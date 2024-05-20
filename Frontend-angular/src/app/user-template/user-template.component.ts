import { Component } from '@angular/core';
import {NavbarService} from "../services/navbar.service";

@Component({
  selector: 'app-user-template',
  templateUrl: './user-template.component.html',
  styleUrl: './user-template.component.css'
})
export class UserTemplateComponent {

  isProfilePage: boolean = false;
  constructor(private navbarService: NavbarService) {
  }

  ngOnInit() {
    this.navbarService.isProfilePage$.subscribe(isProfilePage => {
      this.isProfilePage = isProfilePage;
    });
  }
}
