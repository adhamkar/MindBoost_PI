import { Component } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  /*userImageSrc: string = './app/sidebar/img.png';*/
  userImageSrc: string ='https://theartistgsm.com/wp-content/uploads/2020/08/admin-settings-male.png'
  userName: string = 'User';
  userEmail: string = 'user@gmail.com';
}
