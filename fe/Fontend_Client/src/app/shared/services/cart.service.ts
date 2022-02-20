import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { ObserveOnSubscriber } from 'rxjs/internal/operators/observeOn';
import { CartProduct } from '../models/cart-product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartSubject: BehaviorSubject<CartProduct[]>;

  constructor() {
    this.cartSubject = new BehaviorSubject<CartProduct[]>(JSON.parse(localStorage.getItem('cart')));
  }

  public get getCartQuantity(): number {
    return this.cartSubject.value.length;
  }

  public getCart(): CartProduct[] {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    return cart;
  }

  public addToCart(cartProduct: CartProduct): Observable<any> {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push(cartProduct);
    localStorage.setItem('cart', JSON.stringify(cart));

    let cartSub = new BehaviorSubject(new CartProduct);
    cartSub.next(Object.assign(cartSub.value, cart));
    cartSub.complete();
    return cartSub;
  }

  public saveCart(cartProduct: CartProduct[]): void {
    localStorage.setItem('cart', JSON.stringify(cartProduct));
  }
}
