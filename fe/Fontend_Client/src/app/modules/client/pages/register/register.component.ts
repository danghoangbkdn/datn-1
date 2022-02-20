import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';
import { AlertifyService } from 'src/app/shared/services/alertify.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  status = false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService,
      private alertify: AlertifyService
      ) {
      // redirect to home if already logged in
      if (this.authenticationService.currentUserValue) {
          this.router.navigate(['/']);
      }
    }

  ngOnInit(): void {
      this.registerForm = this.formBuilder.group({
        email: ['', Validators.required],
        password: ['', Validators.required],
        firstname: [''],
        lastname: ['']
      });
      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

    // convinience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        this.loading = true;
        console.log(this.f.email.value, this.f.password.value, this.f.firstname.value, this.f.lastname.value);
        this.authenticationService.register(this.f.email.value, this.f.password.value, this.f.firstname.value, this.f.lastname.value)
              .pipe(first())
              .subscribe(
                  data => {
                      this.alertify.success('Register successful!');
                      this.router.navigate(['/login']);
                      this.status = true;
                  },
                  error => {
                      this.alertify.error('Email is used to register!');
                      this.loading = false;
                      this.status = true;
                  });
    }

}
