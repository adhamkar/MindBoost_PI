import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {NavbarService} from "../services/navbar.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  /*userImageSrc: string = './app/sidebar/img.png';*/
  constructor(public authService: AuthService,private navbarService: NavbarService) {
  }
  userImageSrc: string ='https://theartistgsm.com/wp-content/uploads/2020/08/admin-settings-male.png'
  userName: string = 'User';
  userEmail: string = 'user@gmail.com';

  toggleProfilePage() {
    this.navbarService.setProfilePage(true);
  }
}
