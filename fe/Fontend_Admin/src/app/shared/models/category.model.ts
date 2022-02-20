export class Category {
  id: number;
  name: string;
  parentCategoryId: number;
  parentCategoryName: string;

  public constructor(init?: Partial<Category>) {
    Object.assign(this, init);
  }
}
