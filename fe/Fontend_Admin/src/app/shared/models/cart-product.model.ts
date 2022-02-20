export class  CartProduct {
  cartId: number;
  productId: number;
  product: string;
  price: number;
  quantity: number;

  constructor(init?: Partial<CartProduct>) {
    Object.assign(this, init);
  }
}
