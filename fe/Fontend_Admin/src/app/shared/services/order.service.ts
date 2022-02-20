import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from 'src/constants/constant';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient
  ) { }

  public getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(`${Constant.COMMON_URL}/orders`);
  }

  public getById(id: number): Observable<Order> {
    return this.http.get<Order>(`${Constant.COMMON_URL}/orders/${id}`);
  }

  public updateById(id: number, order: Order): Observable<Order> {
    return this.http.put<Order>(`${Constant.COMMON_URL}/orders/${id}`, order);
  }

  public deteleById(id: number): Observable<any> {
    return this.http.delete(`${Constant.COMMON_URL}/orders/${id}`);
  }

}
