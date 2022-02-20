import { Component, OnInit } from '@angular/core';
import { CartProduct } from 'src/app/shared/models/cart-product.model';
import { OrderProduct } from 'src/app/shared/models/order-product.model';
import { Order } from 'src/app/shared/models/order.model';
import { User } from 'src/app/shared/models/user.model';
import { OrderService } from 'src/app/shared/services/order.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.scss']
})
export class PaymentComponent implements OnInit {
  cartOrder: CartProduct[];
  user: User;
  order: Order;

  constructor(
    private orderServer: OrderService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.getUser();
    this.getOrderProduct();
  }

  public getOrderProduct(): void {
    this.cartOrder = this.orderServer.getOrder();
  }

  public getUser(): void {
    const account = JSON.parse(localStorage.getItem('currentUser'));
    this.userService.getUserById(account?.id).subscribe(user => {
      this.user = user;
      console.log(this.user);
      this.initOrder();
    })
  }

  public initOrder(): void {
    let orderProducts = this.cartOrder.map(item => new OrderProduct({
      orderId: 0,
      productId: item.productId,
      product: item.productName,
      price: item.price,
      quantity: item.quantity
    }));

    let totalPrice = 0;
    totalPrice = orderProducts.map(item => item.price * item.quantity).reduce((item1, item2) => item1 + item2);

    this.order = new Order({
      id: 0,
      userId: this.user.id,
      username: this.user.lastName + ' ' + this.user.firstName,
      address: this.user.address,
      phone: this.user.phone,
      totalCharges: totalPrice,
      status: null,
      shippingFee: 0,
      deliver: null,
      orderDate: null,
      deliveryDate: null,
      promotions: null,
      orderProducts: orderProducts
    })

    console.log(this.order);
  }

  public payment(): void {
    this.orderServer.payment(this.order).subscribe(order => {
      console.log(order);
    })
  }

}
