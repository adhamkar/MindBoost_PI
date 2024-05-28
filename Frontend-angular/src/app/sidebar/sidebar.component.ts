import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import { MatDialog } from '@angular/material/dialog';
import { ManageTaskComponent } from '../manage-task/manage-task.component'

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {

  /*userImageSrc: string = './app/sidebar/img.png';*/
  constructor(public authService: AuthService,public dialog: MatDialog) {
  }
  userImageSrc: string ='https://theartistgsm.com/wp-content/uploads/2020/08/admin-settings-male.png'
  userName: string = 'User';
  userEmail: string = 'user@gmail.com';

  openManageTaskModal() {
    const dialogRef = this.dialog.open(ManageTaskComponent, {
      width: '800px',
      height: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
