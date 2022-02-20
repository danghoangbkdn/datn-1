import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Constant } from '../constants/Constant';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  public getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${Constant.USER_URL}/${id}`);
  }

  public updateUser(userId: number, user: User) {
    return this.http.put<any>(Constant.USER_URL + '/' + userId, user);
  }
}
