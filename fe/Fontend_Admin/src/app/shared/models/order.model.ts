import { OrderProduct } from './order-product.model';
import { Promotion } from './promotion.model';

export class Order {
  id: number;
  userId: number;
  username: string;
  address: string;
  phone: string;
  totalCharges: number;
  status: string;
  shippingFee: number;
  deliver: string;
  orderDate: Date;
  deliveryDate: Date;
  promotions: Array<Promotion>;
  orderProducts: Array<OrderProduct>;

  public constructor(init?: Partial<Order>) {
    return Object.assign(this, init);
  }
}
