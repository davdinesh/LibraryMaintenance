import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
export class BookEditComponent implements OnInit {

  bookForm: FormGroup;
  id:number=null;
  title:string='';
  price:number=null;
  volume:number=null;
  publishedDate:Date=null;
  isLoadingResults = false;
  subjectId: number;

  constructor(private router: Router, private route: ActivatedRoute, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getBook(this.route.snapshot.params['id']);
    this.bookForm = this.formBuilder.group({
      'title' : [null, Validators.required],
      'price' : [null, Validators.required],
      'volume' : [null, Validators.required],
      'publishedDate' : [null, Validators.required]
    });
  }

  getBook(id) {
    this.api.getBook(id).subscribe(data => {
      this.id = data.id;
      this.subjectId = data.subject.id;
      this.bookForm.setValue({
        title: data.title,
        price: data.price,
        volume: data.volume,
        publishedDate: data.publishedDate
      });
    });
  }

  onFormSubmit(form:NgForm) {
    this.isLoadingResults = true;
    this.api.updateBook(this.id, form, this.subjectId)
      .subscribe(res => {
          let id = res['id'];
          this.isLoadingResults = false;
          this.router.navigate(['/boook-detail', id]);
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        }
      );
  }

  bookDetail() {
    this.router.navigate(['/book-detail', this.id]);
  }

}
