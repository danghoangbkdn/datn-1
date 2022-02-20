import { CartProduct } from './cart-product.model';

export class Cart {
  id: number;
  userId: number;
  cartProducts: Array<CartProduct>;

  constructor(init?: Partial<Cart>) {
    Object.assign(this, init);
  }
}
