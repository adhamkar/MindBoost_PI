import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-template',
  templateUrl: './user-template.component.html',
  styleUrl: './user-template.component.css'
})
export class UserTemplateComponent {
  constructor(public authService: AuthService, private router: Router ){
  }

  Onlogout() {
    this.authService.logout();
  }
}
