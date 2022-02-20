import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Constant } from 'src/constants/constant';
import { Account } from '../models/account.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<Account>;
  public currentUser: Observable<Account>;

  constructor(private http: HttpClient) {
      this.currentUserSubject = new BehaviorSubject<Account>(JSON.parse(localStorage.getItem('currentUser')));
      this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): Account {
      return this.currentUserSubject.value;
  }

  login(email: string, password: string) {
      return this.http.post<any>(Constant.LOGIN_URL, { email, password })
          .pipe(map(account => {
              if (account && account.token) {
                  localStorage.setItem('currentUser', JSON.stringify(account));
                  this.currentUserSubject.next(account);
              }
              return account;
          }));
  }



  logout() {
      localStorage.removeItem('currentUser');
      this.currentUserSubject.next(null);
  }
}
