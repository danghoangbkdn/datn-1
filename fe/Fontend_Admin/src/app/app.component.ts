import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpRequest, HttpResponse, HttpClient } from '@angular/common/http';
import { AuthenticationService } from './shared/services/authentication.service';
import { Account } from './shared/models/account.model';

@Component({ selector: 'app-root', templateUrl: 'app.component.html' })
export class AppComponent {
    currentUser: Account;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/admin/login']);
    }
}
