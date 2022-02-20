import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from 'src/app/components/login';
import { AuthGuard } from 'src/app/_helpers';
import { DashboardComponent } from './components/dashboard';
import { AdminLayoutComponent } from './components/layout-admin';
import { UserProfileComponent } from './components/user-profile';
import { UserListComponent } from './components/user-list/user-list.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { CategoryComponent } from './components/layout-admin/pages/category/category.component';
import { ProductComponent } from './components/layout-admin/pages/product/product.component';
import { ProductFormComponent } from './components/layout-admin/pages/product-form/product-form.component';
import { OrderComponent } from './components/layout-admin/pages/order/order.component';
import { OrderFormComponent } from './components/layout-admin/pages/order-form/order-form.component';

const routes: Routes = [
    { path: 'admin/login', component: LoginComponent },
    {
      path: '',
      component: AdminLayoutComponent,
      children: [
          { path: '',      component: DashboardComponent },
          { path: 'dashboard',      component: DashboardComponent },
          { path: 'user-profile',   component: UserProfileComponent },
          { path: 'user-list',     component: UserListComponent },
          { path: 'categories',     component: CategoryComponent },
          { path: 'products',     component: ProductComponent },
          { path: 'products/create',     component: ProductFormComponent },
          { path: 'products/update/:id',     component: ProductFormComponent },
          { path: 'orders',     component: OrderComponent },
          { path: 'orders/update/:id',     component: OrderFormComponent},
      ]
    },

    // otherwise redirect to home
  //  { path: '', pathMatch: 'full', redirectTo: '/login' },
  //  { path: '**', redirectTo: '' }
];

export const appRoutingModule = RouterModule.forRoot(routes);
