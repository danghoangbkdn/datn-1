import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { LocalStorageEnum } from '../enums/local-storage.enum';
import { Account } from '../models/account.model';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHttpInterceptorService implements HttpInterceptor {
  private currentUser: Account;

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (localStorage.getItem(LocalStorageEnum.USER)) {
      this.currentUser = JSON.parse(localStorage.getItem(LocalStorageEnum.USER));
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.currentUser.token
        },
      });
    }
    return next.handle(req);
  }

}
