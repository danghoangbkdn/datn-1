import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../shared/models/account.model';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHttpInterceptorService implements HttpInterceptor {
  currentUser: Account;
  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (localStorage.getItem('currentUser')) {
      this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.currentUser.token
        },
      });
    }
    return next.handle(req);
  }

}
