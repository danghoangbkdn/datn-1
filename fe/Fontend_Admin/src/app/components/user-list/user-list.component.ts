import { AlertService } from './../../_services/alert.service';
import { HttpEvent } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/_models/user';
import { UserService } from 'src/app/_services';
import { first } from 'rxjs/operators';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { start } from 'repl';
import { Account } from 'src/app/shared/models/account.model';
import { AuthenticationService } from 'src/app/shared/services/authentication.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  currentUser: Account;
  currentUserSubscription: Subscription;
  lastestUsers: User[] = [];
  users: User[] = [];
  disabledUsers: User[] = [];
  userData: any;
  showLastestUser = true;
  showActiveUser = false;
  showDisabledUser = false;

  // For Pagination
  currentPage = 1;
  startPage = 1;
  endPage = 1;
  totalRecords = 0;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private userService: UserService,
    private alertService: AlertService
  ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
      this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
          this.currentUser = user;
      });
  }

  ngOnInit(): void {
    this.getLastestUsers();
    this.getEnabledUser();
    this.getUserDisabled();
  }

  getUserDisabled() {
    this.userService.getUserDisabled().subscribe(response => {
      this.disabledUsers = response.Data;
    })
  }

  getLastestUsers() {
    this.userService.getLastestUsers().pipe(first()).subscribe(response => {
      this.lastestUsers = response.Data;
    });
  }

  getEnabledUser() {
    this.userService.getAll(1).pipe(first()).subscribe(response => {
      this.users = response.Data;
      this.totalRecords = response.TotalRecords;
      this.endPage = Math.floor(this.totalRecords / 5) + (this.totalRecords % 5 === 0 ? 0 : 1);
    });
  }

  onChange(userId: number, username: string, event) {
      const checked = event.target.checked;
      if (!checked) {
        this.userService.updateUserByAdmin(userId, 0).subscribe(
          data => {
            this.alertService.success("Updated user " + username, true);
            this.getLastestUsers();
            this.getEnabledUser();
            this.getUserDisabled();
          },
          error => {
            this.alertService.error("Cannot update user " + username, true);
          }
        )
      }
    }

    onActive(userId: number, username: string, event) {
      const checked = event.target.checked;
      if (checked) {
        this.userService.updateUserByAdmin(userId, 1).subscribe(
          data => {
            this.alertService.success("Updated user " + username, true);
            this.getLastestUsers();
            this.getEnabledUser();
            this.getUserDisabled();
          },
          error => {
            this.alertService.error("Cannot update user " + username, true);
          }
        )
      }
    }

    public getNextPage() {
        this.userService.getAll(this.currentPage + 1).subscribe(
          response => {
            this.users = response.Data;
            this.totalRecords = response.TotalRecords;
            this.endPage = Math.floor(this.totalRecords / 5) + (this.totalRecords % 5 === 0 ? 0 : 1);
            this.currentPage++;
          }
        );
    }

    public getPrePage() {
      this.userService.getAll(this.currentPage - 1).subscribe(
        response => {
          this.users = response.Data;
          this.totalRecords = response.TotalRecords;
          this.endPage = Math.floor(this.totalRecords / 5) + (this.totalRecords % 5 === 0 ? 0 : 1);
          this.currentPage--;
        }
      );
    }

    public getStartPage() {
      this.userService.getAll(this.startPage).subscribe(
        response => {
          this.users = response.Data;
          this.totalRecords = response.TotalRecords;
          this.endPage = Math.floor(this.totalRecords / 5) + (this.totalRecords % 5 === 0 ? 0 : 1);
          this.currentPage = this.startPage;
        }
      );
    }

    public getEndPage() {
      this.userService.getAll(this.endPage).subscribe(
        response => {
          this.users = response.Data;
          this.totalRecords = response.TotalRecords;
          this.endPage = Math.floor(this.totalRecords / 5) + (this.totalRecords % 5 === 0 ? 0 : 1);
          this.currentPage = this.endPage;
        }
      );
    }
}

