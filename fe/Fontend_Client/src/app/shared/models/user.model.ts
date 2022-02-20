export class User {
  id: number;
  firstName: string;
  lastName: string;
  gender: boolean;
  birthday: Date;
  phone: string;
  address: string;

  constructor(init?: Partial<User>) {
    Object.assign(this, init);
  }

  fullName(): string {
    return `${this.firstName} ${this.lastName}`;
  }

  getGender(): string {
    return this.gender ? 'Nữ' : 'Nam';
  }
}
