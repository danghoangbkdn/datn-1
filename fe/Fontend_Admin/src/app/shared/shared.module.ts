import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import { DeleteConfirmComponent } from './components/delete-confirm/delete-confirm.component';
import { MatSelectModule } from '@angular/material/select';
@NgModule({
  declarations: [DeleteConfirmComponent],
  imports: [
    CommonModule,
    MatDialogModule,
    MatSelectModule
  ],
  entryComponents: [
    DeleteConfirmComponent,
  ],
})
export class SharedModule { }
