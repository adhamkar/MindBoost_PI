import { Component, HostListener } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {EditProfileComponent} from "./edit-profile/edit-profile.component";
import {ProfileService} from "../services/profile.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  innerWidth: any;
  public profileData: any;
  constructor(private dialog: MatDialog, private profileService: ProfileService) {
    this.profileData = this.profileService.getProfileData();
  }

  openEditProfileDialog(): void {
    const dialogRef = this.dialog.open(EditProfileComponent, {
      width: '570px',
      data: {
        profile: this.profileData
      }
    });

    dialogRef.afterClosed().subscribe(result => {

      // Traitez ici les résultats de la boîte de dialogue, par exemple, mettez à jour les informations du profil
    });
  }



  ngOnInit(): void {
    this.innerWidth = window.innerWidth;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.innerWidth = window.innerWidth;
  }

  getClass(): string {
    return this.innerWidth < 925 ? 'row-md' : 'row';
  }
}
