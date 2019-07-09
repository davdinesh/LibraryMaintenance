import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap, map } from 'rxjs/operators';
import { Book } from './model/book';
import { SubjectModel } from './model/subject.model';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

const apiUrl = "api/books";
const apiSubjectUrl = "api/subjects";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  getSubjects (): Observable<SubjectModel[]> {
    return this.http.get<SubjectModel[]>(apiSubjectUrl)
      .pipe(
        tap(heroes => console.log('fetched products')),
        catchError(this.handleError('getBooks', []))
      );
  }

  getBooks (): Observable<Book[]> {
    return this.http.get<Book[]>(apiUrl+"/")
      .pipe(
        tap(heroes => console.log('fetched Books')),
        catchError(this.handleError('getBooks', []))
      );
  }

  getBook (id : number): Observable<Book> {
    const url = apiUrl + "/" + id;
    return this.http.get<Book>(url)
      .pipe(
        tap(_ => console.log('fetched book id=' + id)),
        catchError(this.handleError<Book>('getBook id=${id}'))
      );
  }

  addBook (book, subjectId : number): Observable<Book> {
    return this.http.post<Book>(apiUrl+"/"+subjectId, book, httpOptions).pipe(
      tap((book: Book) => console.log('added product w/ id=' + book.id)),
      catchError(this.handleError<Book>('addBook'))
    );
  }

  updateBook(id : number, book, subjectId : number): Observable<any> {
    const url = apiUrl + "/" + id + "/" + subjectId;
    return this.http.put(url, book, httpOptions).pipe(
      tap(_ => console.log('updated book id=${id}')),
      catchError(this.handleError<any>('updateBook'))
    );
  }

  deleteBook (id : number): Observable<Book> {
    const url = `${apiUrl}/${id}`;
  
    return this.http.delete<Book>(url, httpOptions).pipe(
      tap(_ => console.log('deleted book id=${id}')),
      catchError(this.handleError<Book>('deleteBook'))
    );
  }

}
