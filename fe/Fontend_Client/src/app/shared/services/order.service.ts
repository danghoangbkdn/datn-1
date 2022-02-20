import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from '../constants/Constant';
import { CartProduct } from '../models/cart-product.model';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private http: HttpClient
  ) { }

  public getByUser(userId: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${Constant.API_URL}/orders/user/${userId}`);
  }

  public getById(id: number): Observable<Order> {
    return this.http.get<Order>(`${Constant.API_URL}/orders/${id}`);
  }

  public getOrder(): CartProduct[] {
    return JSON.parse(localStorage.getItem('payment'));
  }

  public payment(order: Order): Observable<Order> {
    return this.http.post<Order>(`${Constant.API_URL}/orders/user/${order.userId}`, order);
  }

  public update(order: Order): Observable<Order> {
    return this.http.put<Order>(`${Constant.API_URL}/orders/user/${order.userId}/order/${order.id}`, order);
  }

}
