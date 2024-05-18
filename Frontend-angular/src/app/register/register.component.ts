import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Patient } from "../interfaces/patient";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      username:this.fb.control(null, Validators.required),
      email: this.fb.control(null, [Validators.required, Validators.email]),
      password: this.fb.control(null, Validators.required),
      gender: this.fb.control(null, Validators.required),
      age: this.fb.control('', Validators.required),
      city: this.fb.control(null, Validators.required),
      profession: this.fb.control(null, Validators.required),
      phone: this.fb.control(null, Validators.required),
      medicalHistory: this.fb.control(null),
    });
  }

  handleSignUp(): void {
      let patientData: Patient = this.registerForm.value ;
      this.authService.signup(patientData).subscribe(
        (data) => {
          this.authService.LoadProfile(data);
          this.router.navigateByUrl("/home");
        },
        (error) => {
          console.log(this.registerForm.valid);
          console.log('Signup failed',error);
        }
      );

  }

}
