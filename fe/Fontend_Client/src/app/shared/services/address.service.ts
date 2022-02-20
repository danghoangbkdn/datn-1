import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { addressData } from 'src/assets/data/dvhc_data.json';
import { Constant } from '../constants/Constant';
import { Address } from '../models/address.model';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
  constructor(private http: HttpClient) {}
  PROVINCE_LEVEL = 'TINH';
  DISTRICT_LEVEL = 'HUYEN';
  WARD_LEVEL = 'XA';
  UPPER_UNIT_LEVEL = { HUYEN: this.PROVINCE_LEVEL, XA: this.DISTRICT_LEVEL };

  public getSugestedProvinces(): string[] {
    return this.getAdminUnits(this.PROVINCE_LEVEL);
  }

  public getSugestedDistricts(province: string): string[] {
    return this.getAdminUnits(this.DISTRICT_LEVEL, province);
  }

  public getSugestedWards(district: string): string[] {
    return this.getAdminUnits(this.WARD_LEVEL, district);
  }

  public getAdminUnits(level: string, upperUnitName?: string): string[] {
    if (level === this.PROVINCE_LEVEL) {
      return addressData
        .filter((data) => data.Cap === this.PROVINCE_LEVEL)
        .map((data) => data.Ten)
        .sort((a, b) => a.localeCompare(b));
    }
    if (!upperUnitName) {
      return;
    }
    const upperUnit = addressData.find(
      (data) =>
        data.Ten === upperUnitName && data.Cap === this.UPPER_UNIT_LEVEL[level]
    );
    return addressData
      .filter((data) => data.Cap === level && data.CapTren === upperUnit.MaDVHC)
      .map((data) => data.Ten)
      .sort((a, b) => a.localeCompare(b));
  }

  public getUserAddresses(id: number): Observable<Address[]> {
    return this.http.get<Address[]>(`${Constant.USER_ADDRESS_URL}/user/${id}`);
  }

  public getUserAddressById(
    userId: number,
    addressId: number
  ): Observable<Address> {
    return this.http.get<Address>(
      `${Constant.USER_ADDRESS_URL}/user/${userId}/address/${addressId}`
    );
  }

  public addUserAddresses(id: number, address: Address): Observable<Address> {
    address.country = "Việt Nam";
    return this.http.post<Address>(
      `${Constant.USER_ADDRESS_URL}/user/${id}`,
      address
    );
  }

  public updateUserAddresses(userId: number, address: Address) {
    return this.http.put<Address>(
      `${Constant.USER_ADDRESS_URL}/user/${userId}/address/${address.addressId}`,
      address
    );
  }

  public deleteUserAddresses(userId: number, addressId: number) {
    return this.http.delete<Address>(
      `${Constant.USER_ADDRESS_URL}/user/${userId}/address/${addressId}`
    );
  }
}
