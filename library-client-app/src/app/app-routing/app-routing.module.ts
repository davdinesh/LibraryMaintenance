import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { BooksComponent } from '../books/books.component';
import { BookAddComponent } from '../book-add/book-add.component';
import { BookDetailComponent } from '../book-detail/book-detail.component';
import { BookEditComponent } from '../book-edit/book-edit.component';

const routes: Routes = [
  {
    path: 'books',
    component: BooksComponent,
    data: { title: 'List of Books' }
  },
  {
    path: 'book-detail/:id',
    component: BookDetailComponent,
    data: { title: 'Book Detail' }
  },
  {
    path: 'book-add',
    component: BookAddComponent,
    data: { title: 'Add Book' }
  },
  {
    path: 'book-edit/:id',
    component: BookEditComponent,
    data: { title: 'Edit Book' }
  },
  { path: '',
    redirectTo: '/books',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
