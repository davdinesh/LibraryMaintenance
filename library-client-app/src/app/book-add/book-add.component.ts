import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../api.service';
import { FormControl, FormGroupDirective, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { SubjectModel } from '../model/subject.model'


@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class BookAddComponent implements OnInit {

  bookForm: FormGroup;
  id:string='';
  title:string='';
  price:number=null;
  volume:number=null;
  publishedDate:Date=null;
  isLoadingResults = false;
  subjectId: number;
  subjects: SubjectModel[] = [];
  constructor(private router: Router, private api: ApiService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      'title' : [null, Validators.required],
      'price' : [null, Validators.required],
      'volume' : [null, Validators.required],
      'publishedDate' : [null, Validators.required]
    });
    this.api.getSubjects()
    .subscribe(res => {
      this.subjects = res;
      console.log(this.subjects);
      this.isLoadingResults = false;
    }, err => {
      console.log(err);
      this.isLoadingResults = false;
    });
  }

  onFormSubmit(form:NgForm) {
    this.isLoadingResults = true;
    this.api.addBook(form,this.subjectId)
      .subscribe(res => {
          let id = res['id'];
          this.isLoadingResults = false;
          this.router.navigate(['/book-detail', id]);
        }, (err) => {
          console.log(err);
          this.isLoadingResults = false;
        });
  }

  selectedclient(event) {
    console.log(event.option.value);
    this.subjectId = event.option.value.id;
    console.log("Subject id : " + this.subjectId)
  }

  getOptionText(option) {
    return option.title;
  }

}
