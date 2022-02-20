import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Address } from 'src/app/shared/models/address.model';
import { AddressService } from 'src/app/shared/services/address.service';
import { AlertifyService } from 'src/app/shared/services/alertify.service';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';

@Component({
  selector: 'app-addresses',
  templateUrl: './addresses.component.html',
  styleUrls: ['./addresses.component.scss'],
})
export class AddressesComponent implements OnInit {
  public userAddresses: Address[];
  private userId: number;
  constructor(
    private authService: AuthenticationService,
    private addressService: AddressService,
    private alertService: AlertifyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const currentAccount = this.authService.currentUserValue;
    this.userId = currentAccount.userId;
    this.addressService
      .getUserAddresses(this.userId)
      .subscribe((addresses: Address[]) => {
        this.userAddresses = addresses;
      });
  }

  delete(addressId: number): void {
    this.addressService.deleteUserAddresses(this.userId, addressId).subscribe(
      (_) => {
        this.alertService.success('Delete successfully!');
        this.ngOnInit();
      },
      (_) => {
        this.alertService.error('Failed to delete!');
      }
    );
  }
}
