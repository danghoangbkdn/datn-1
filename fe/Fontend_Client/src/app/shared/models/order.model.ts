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
  promotions: Set<Promotion>;
  orderProducts: Array<OrderProduct>;

  constructor(init?: Partial<Order>) {
    Object.assign(this, init);
  }
}
