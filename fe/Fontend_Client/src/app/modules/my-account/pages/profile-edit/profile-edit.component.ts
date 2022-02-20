import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/shared/models/user.model';
import { AlertifyService } from 'src/app/shared/services/alertify.service';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';
import { UserService } from 'src/app/shared/services/user.service';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.scss'],
})
export class ProfileEditComponent implements OnInit {
  public loading = false;
  public userId: number;
  public profileForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.minLength(2),
      Validators.maxLength(20),
    ]),
    lastName: new FormControl(''),
    gender: new FormControl(''),
    birthday: new FormControl('', this.birthdayValidator),
    phone: new FormControl('', [
      Validators.pattern('^[0-9]*$'),
      Validators.minLength(10),
      Validators.maxLength(12),
    ]),
  });

  constructor(
    private authService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertifyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const currentAccount = this.authService.currentUserValue;
    this.userId = currentAccount.userId;
    this.userService
      .getUserById(currentAccount.userId)
      .subscribe((user: User) => {
        this.profileForm.patchValue(user);
      });
  }

  public save(): void {
    this.loading = true;
    console.log(this.profileForm.value);
    this.userService.updateUser(this.userId, this.profileForm.value).subscribe(
      (_) => {
        this.alertService.success('Đã lưu thông tin!');
        this.router.navigate(['/my-account/profile']);
        this.loading = false;
      },
      (_) => {
        this.loading = false;
        this.alertService.error('Đã xảy ra lỗi!');
      }
    );
  }

  private birthdayValidator(
    control: AbstractControl
  ): { [key: string]: boolean } | null {
    const date = new Date(control.value);
    const toDay = new Date();
    return date > toDay ? { invalidDate: true } : null;
  }
}
