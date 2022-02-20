import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from 'protractor';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  // @Input() form: FormGroup;
  // @Output() submitEmitter = new EventEmitter();
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  get f() {return this.form.controls; }

  private initForm() {
    this.form = this.formBuilder.group({
      title: [null, Validators.required],
      author: [null, Validators.required],
      description: [null, Validators.required],
      image: [null, Validators.required],
    });
  }

  onSubmit() {

    if (this.form.invalid) {
      return;
    }

    //this.submitEmitter.emit('book-form -shared');
    console.log(this.f.title.value, this.f.author.value, this.f.description.value, this.f.image.value);
  }

}
