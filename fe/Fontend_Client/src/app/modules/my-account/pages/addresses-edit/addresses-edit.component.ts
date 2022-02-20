import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Address } from 'src/app/shared/models/address.model';
import { AddressService } from 'src/app/shared/services/address.service';
import { AlertifyService } from 'src/app/shared/services/alertify.service';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';

@Component({
  selector: 'app-addresses-edit',
  templateUrl: './addresses-edit.component.html',
  styleUrls: ['./addresses-edit.component.scss'],
})
export class AddressesEditComponent implements OnInit {
  PROVINCE_LEVEL = 'TINH';
  DISTRICT_LEVEL = 'HUYEN';
  WARD_LEVEL = 'XA';
  public userId: number;
  public pageTitle = 'Thêm mới địa chỉ';
  public provinceOptions: string[];
  public districtOptions: string[];
  public wardOptions: string[];
  public addressForm = new FormGroup({
    addressId: new FormControl(''),
    province: new FormControl('', [Validators.required]),
    district: new FormControl({ value: '', disabled: true }, [Validators.required]),
    ward: new FormControl({ value: '', disabled: true }, [Validators.required]),
    streetNumber: new FormControl('', [Validators.maxLength(10)]),
    streetName: new FormControl('', [Validators.required]),
    defaultAddress: new FormControl(false),
  });
  public loading = false;
  private saveService = 'addUserAddresses';

  constructor(
    private authService: AuthenticationService,
    private addressService: AddressService,
    private route: ActivatedRoute,
    private alertService: AlertifyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initAddForm();
    this.route.params.subscribe((params: Params) => {
      const addressId = params.id;
      if (addressId === '0') {
        return;
      }
      this.initEditForm(addressId);
    });
  }

  save(): void {
    this.loading = true;
    this.addressService[this.saveService](this.userId, this.addressForm.value).subscribe(
      (_) => {
        this.alertService.success('Update successfully!');
        this.router.navigate(['/my-account/addresses']);
      },
      (_) => {
        this.loading = false;
        this.alertService.error('Failed to update!');
      }
    );
  }

  initAddForm(): void {
    this.userId = this.authService.currentUserValue.userId;
    this.initSelectors();
  }

  initEditForm(addressId: number): void {
    this.pageTitle = 'Sửa địa chỉ';
    this.saveService = 'updateUserAddresses';
    this.addressService
      .getUserAddressById(this.userId, addressId)
      .subscribe((address) => {
        this.initSelectors(address);
        this.addressForm.patchValue(address);
      });
  }

  initSelectors(address?: Address): void {
    this.provinceOptions = this.addressService.getSugestedProvinces();
    if (!address) {
      return;
    }
    this.addressForm.controls.district.enable();
    this.addressForm.controls.ward.enable();
    this.districtOptions = this.addressService.getSugestedDistricts(address.province);
    this.wardOptions = this.addressService.getSugestedWards(address.district);
  }

  handleSelectorsChange(level: string): void {
    switch (level) {
      case this.PROVINCE_LEVEL:
        this.districtOptions = this.addressService.getSugestedDistricts(this.addressForm.value.province);
        this.addressForm.controls.district.enable();
        this.addressForm.controls.district.setValue('');
        this.addressForm.controls.ward.disable();
        this.addressForm.controls.ward.setValue('');
        break;
      case this.DISTRICT_LEVEL:
        this.wardOptions = this.addressService.getSugestedWards(this.addressForm.value.district);
        this.addressForm.controls.ward.enable();
        this.addressForm.controls.ward.setValue('');
        break;
      case this.WARD_LEVEL:
        return;
    }
  }
}
