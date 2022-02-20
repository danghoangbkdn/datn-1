import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/_models/user';
import { UserService } from 'src/app/_services';
import { first } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  public user: User;
  public username: string;
  public detail: string;
  public email: string;
  public updateProfile: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService) {
      this.username = '';
      this.detail = '';
      this.email = '';
    }

  ngOnInit(): void {
    this.getProfile();
    this.updateProfile = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      detail: ['', Validators.required],
    });
  }

  getProfile() {
    this.userService.getProfile().pipe(first()).subscribe(reponse => {
      this.user = reponse;
      this.username = this.user.username;
      this.detail = this.user.detail;
      this.email = this.user.email;
      this.f.username.setValue(this.user.username);
      this.f.password.setValue(this.user.password);
      this.f.detail.setValue(this.user.detail);
    });
  }

  get f() { return this.updateProfile.controls; }

  onSubmit() {
    console.log(this.f.password.value);
    this.userService.updateProfile(this.f.username.value, this.f.password.value, this.f.detail.value)
      .pipe(first())
      .subscribe(reponse => {
        console.log(reponse);
        this.user = reponse;
        console.log(this.user);
      })
  }
}
