import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/shared/models/order.model';
import { OrderService } from 'src/app/shared/services/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent implements OnInit {
  orders: Order[];

  constructor(
    private router: Router,
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.getAllOrder();
  }

  public getAllOrder(): void {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    this.orderService.getByUser(user.id).subscribe((orders: Order[]) => {
      this.orders = orders;
    })
  }

  public getDetailOrder(id: number): void {
    console.log(id);
    this.router.navigate(['/my-account/orders', id]);
  }

}
