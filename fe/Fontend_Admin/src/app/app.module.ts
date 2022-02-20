import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { LoginComponent } from './components/login/login.component';
import { AlertComponent } from './components/alert/alert.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AdminLayoutComponent } from './components/layout-admin/layout-admin.component';
import { NavbarComponent } from './components/componentsUtil/navbar/navbar.component';
import { FooterComponent } from './components/componentsUtil/footer/footer.component';
import { SidebarComponent } from './components/componentsUtil/sidebar/sidebar.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { AppComponent } from './app.component';
import { BasicAuthHttpInterceptorService } from './_services/basic-auth-http-interceptor.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { appRoutingModule } from './app.routing';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field/index';
import { MatInputModule } from '@angular/material/input';
import { CategoryComponent } from './components/layout-admin/pages/category/category.component';
import { SharedModule } from './shared/shared.module';
import { ProductComponent } from './components/layout-admin/pages/product/product.component';
import { ProductFormComponent } from './components/layout-admin/pages/product-form/product-form.component';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { EntityComponent } from './components/layout-admin/components/entity/entity.component';
import { MatNativeDateModule } from '@angular/material/core';
import { OrderComponent } from './components/layout-admin/pages/order/order.component';
import { OrderFormComponent } from './components/layout-admin/pages/order-form/order-form.component';

@NgModule({
  imports: [
      BrowserModule,
      BrowserAnimationsModule,
      ReactiveFormsModule,
      HttpClientModule,
      appRoutingModule,
      FormsModule,
      MatFormFieldModule,
      MatInputModule,
      NgbModule,
      MatDialogModule,
      MatSelectModule,
      MatNativeDateModule,
      MatDatepickerModule,
      SharedModule
  ],
  declarations: [
      AppComponent,
      LoginComponent,
      AlertComponent,
      DashboardComponent,
      AdminLayoutComponent,
      NavbarComponent,
      FooterComponent,
      SidebarComponent,
      UserProfileComponent,
      UserListComponent,
      PostListComponent,
      CategoryComponent,
      ProductComponent,
      ProductFormComponent,
      EntityComponent,
      OrderComponent,
      OrderFormComponent
  ],
  providers: [
      { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHttpInterceptorService, multi: true },
      AppComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
