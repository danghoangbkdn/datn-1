import { Category } from './category.model';
import { Promotion } from './promotion.model';
import { Review } from './review.model';

export class Product {
  id: number;
  name: string;
  supplier: string;
  description: string;
  originPrice: number;
  image: string;
  covers?: string[];
  categories: Category[];
  promotions: Promotion[];
  reviews: Review[];
}
