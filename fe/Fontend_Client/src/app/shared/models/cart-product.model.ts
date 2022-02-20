export class  CartProduct {
  cartId: number;
  productId: number;
  productName: string;
  productCover: string;
  productDescription: string;
  price: number;
  quantity: number;
  status: boolean;

  constructor(init?: Partial<CartProduct>) {
    Object.assign(this, init);
  }
}
