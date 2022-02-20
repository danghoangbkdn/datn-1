import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BookFormComponent } from './components/book-form/book-form.component';
import { AlertifyService } from './services/alertify.service';
import { ConfirmComponent } from './components/confirm/confirm.component';
import { MatDialogModule } from '@angular/material/dialog';

const modules = [
  FormsModule,
  ReactiveFormsModule,
  MatDialogModule,
];

@NgModule({
  declarations: [
     BookFormComponent,
     ConfirmComponent,
  ],
  imports: [
    CommonModule,
    ...modules
  ],
  exports: [
    ...modules,
    BookFormComponent
  ]
})
export class SharedModule { }
