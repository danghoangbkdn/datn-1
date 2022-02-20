export class Address {
  id?: number;
  addressId?: number;
  userId: number;
  country: string;
  province: string;
  district: string;
  ward: string;
  streetName: string;
  streetNumber: string;
  addressType: string;
  defaultAddress: boolean;
}
