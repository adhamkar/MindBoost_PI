import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {UserRoleComponent} from "../user-role/user-role.component";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  formLogin!: FormGroup;
  patient!: string;
  therapist!: string;
  ngOnInit(): void {
    this.formLogin = this.fb.group({
      username: this.fb.control('',Validators.required),
      password: this.fb.control('',Validators.required)
    });
  }
  constructor(private fb: FormBuilder, private authService: AuthService,private router: Router,public dialog: MatDialog){

  }


  HandleLogin() {
    let username = this.formLogin.value.username;
    let password = this.formLogin.value.password;
   this.authService.login(username,password).subscribe({
      next: (data) => {
        console.log('Data from backend:', data);
        this.authService.LoadProfile(data);
        this.router.navigateByUrl("/user/posts");
      },
      error: (err) => {
        console.log(err);
      }
   })
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(UserRoleComponent, {
      height: '250px',
      width: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
