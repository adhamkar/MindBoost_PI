import {Component, HostListener, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {EditProfileComponent} from "./edit-profile/edit-profile.component";
import {ProfileService} from "../services/profile.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  myProfil:any;
  innerWidth: any;
  public profileData: any;
  constructor(private dialog: MatDialog, private profileService: ProfileService,public authService:AuthService) {
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
    this.DisplayProfile(this.authService.getUserId());
    this.innerWidth = window.innerWidth;

  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.innerWidth = window.innerWidth;
  }
DisplayProfile(name:any){
  this.profileService.getProfile(name).subscribe((data)=>{
      console.log(data);
    this.myProfil = data;

  },
  (error: any) => {
    console.log(error);
  })
}
  getClass(): string {
    return this.innerWidth < 925 ? 'row-md' : 'row';
  }
}
