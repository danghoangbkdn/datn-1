export class Entity {
  id: number;
  storageId: number;
  productId: number;
  entryDay: Date;
  totalQuantity: number;
  quantity: number;

  public constructor(init?: Partial<Entity>) {
    Object.assign(this, init);
  }
}
