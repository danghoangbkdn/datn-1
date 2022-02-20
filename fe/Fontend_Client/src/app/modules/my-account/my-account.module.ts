import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';

import { MyAccountRoutingModule } from './my-account-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ProfileEditComponent } from './pages/profile-edit/profile-edit.component';
import { OrderDetailComponent } from './pages/order-detail/order-detail.component';
import { OrderListComponent } from './pages/order-list/order-list.component';
import { AddressesEditComponent } from './pages/addresses-edit/addresses-edit.component';
import { AddressesComponent } from './pages/addresses/addresses.component';

@NgModule({
  declarations: [
    ProfileEditComponent,
    OrderListComponent,
    OrderDetailComponent,
    AddressesComponent,
    AddressesEditComponent
  ],
  imports: [
    CommonModule,
    MyAccountRoutingModule,
    SharedModule,
    CKEditorModule,
    MatSortModule,
    MatTableModule,
  ]
})
export class MyAccountModule { }
