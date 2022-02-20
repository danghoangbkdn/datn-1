import { Category } from './category.model';
import { Promotion } from './promotion.model';
import { Review } from './review.model';

export class Product {
  id: number;
  name: string;
  supplier: string;
  description: string;
  originPrice: number;
  quantity: number;
  image: string;
  files?: File[];
  categories: Category[];
  promotions: Promotion[];

  constructor(init?: Partial<Product>) {
    Object.assign(this, init);
  }
}
