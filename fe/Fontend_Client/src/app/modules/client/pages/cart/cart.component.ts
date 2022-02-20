import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartProduct } from 'src/app/shared/models/cart-product.model';
import { CartService } from 'src/app/shared/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  cartProducts: CartProduct[];
  cartOrder: CartProduct[];
  chooseAll: boolean;
  provisionalPrice = 0;
  totalPrice = 0;

  constructor(
    private router: Router,
    private cartService: CartService
  ) {
    this.cartOrder = [];
  }

  ngOnInit(): void {
    this.getCart();
  }

  private getCart(): void {
    this.cartProducts = this.cartService.getCart();
    this.calculProductPrice();
  }

  public goPayment(): void {
    if (this.cartOrder.length > 0) {
      localStorage.setItem('payment', JSON.stringify(this.cartOrder));
      this.router.navigate(['/payment']);
    }
  }

  public changeCart(index: number, item: CartProduct): void {
    item.status = !item.status;
    this.cartProducts[index] = item;
    this.cartService.saveCart(this.cartProducts);
    this.calculProductPrice();
  }

  public deleteItem(index: number, item: CartProduct) {
    this.cartProducts.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(this.cartProducts));
    this.cartService.saveCart(this.cartProducts);
    this.calculProductPrice();
  }

  public subQuantityItem(index: number, item: CartProduct) {
    item.quantity -= 1;
    this.cartProducts[index] = item;
    this.cartService.saveCart(this.cartProducts);
    this.calculProductPrice();
  }

  public addQuantityItem(index: number, item: CartProduct) {
    item.quantity += 1;
    this.cartProducts[index] = item;
    this.cartService.saveCart(this.cartProducts);
    this.calculProductPrice();
  }

  public calculProductPrice(): void {
    this.provisionalPrice = 0;
    this.cartOrder = [];
    this.cartProducts.forEach(item => {
      if (item.status) {
        this.provisionalPrice += item.price * item.quantity;
        this.cartOrder.push(item);
      }
    })
    this.totalPrice = this.provisionalPrice;
  }
}
