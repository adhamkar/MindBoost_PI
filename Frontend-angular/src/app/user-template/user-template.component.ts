import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";
import { ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';


@Component({
  selector: 'app-user-template',
  templateUrl: './user-template.component.html',
  styleUrl: './user-template.component.css'
})
export class UserTemplateComponent {
  @ViewChild('sidebar') sidebar!: MatSidenav;
  isAuth: boolean = false;
  isProfilePage: boolean = false;
  isSidebarOpened = true;

  constructor(public authService: AuthService, private router: Router) {
  }

  ngOnInit() {
this.CheckUserAuth();
  }

  HandleLogout() {
    this.authService.logout();
    this.isAuth = false;
  }

  CheckUserAuth() {
    if (this.authService.getUserId() != null) {
      this.isAuth = true;
    }
  }
}
