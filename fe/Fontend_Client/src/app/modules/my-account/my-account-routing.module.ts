import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderDetailComponent } from './pages/order-detail/order-detail.component';
import { OrderListComponent } from './pages/order-list/order-list.component';
import { AddressesEditComponent } from './pages/addresses-edit/addresses-edit.component';
import { AddressesComponent } from './pages/addresses/addresses.component';
import { ProfileEditComponent } from './pages/profile-edit/profile-edit.component';

const routes: Routes = [
  {
    path: 'profile',
    children: [
      {
        path: '',
        component: ProfileEditComponent,
      }
    ],
  },
  {
    path: 'orders',
    children: [
      {
        path: '',
        component: OrderListComponent,
      },
      {
        path: ':id',
        component: OrderDetailComponent,
      }
    ],
  },
  {
    path: 'addresses',
    children: [
      {
        path: '',
        component: AddressesComponent,
      },
      {
        path: ':id/edit',
        component: AddressesEditComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MyAccountRoutingModule {}
