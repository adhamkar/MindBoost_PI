import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  formLogin!: FormGroup;
  ngOnInit(): void {
    this.formLogin = this.fb.group({
      username: this.fb.control(""),
      password: this.fb.control("")
    });
  }
  constructor(private fb: FormBuilder, private authService: AuthService,private router: Router){

  }


  HandleLogin() {
    let username = this.formLogin.value.username;
    let password = this.formLogin.value.password;
   this.authService.login(username,password).subscribe({
      next: (data) => {
        this.authService.LoadProfile(data);
        this.router.navigate(["/user"]);
      },
      error: (err) => {
        console.log(err);
      }
   })
  }
}
