import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Therapist} from "../models/therapist.model";
import {TherapistService} from "../services/therapist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-therapist',
  templateUrl: './add-therapist.component.html',
  styleUrl: './add-therapist.component.css'
})
export class AddTherapistComponent implements OnInit{
  formTherapist!: FormGroup;
  tharapist:any;

  constructor(private fb: FormBuilder, private tharapistService:TherapistService, private router: Router) {

  }

  ngOnInit(): void {
    this.formTherapist = new FormGroup({
      'userName': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone': new FormControl(null, Validators.required),
      'gender': new FormControl(null, Validators.required),
      'password': new FormControl(null, [Validators.required, Validators.minLength(6)]),
      'therapistRole': new FormControl(null, Validators.required),
      'aviability': new FormControl(false),
      'price': new FormControl(null, [Validators.required, Validators.min(0)]),
      'localAddress': new FormControl(null, Validators.required)
    });
  }

  handleAddTherapist(): void {
    if (this.formTherapist.valid) {
      const newTherapist: Therapist = this.formTherapist.value;
      console.log('New Therapist:', newTherapist);
      // Perform the action to add a new therapist, e.g., call a service to save the therapist.
    }
  }
  SaveTherapist(){
    this.tharapist= this.formTherapist.value;
    this.tharapistService.addTherapist(this.tharapist).subscribe(
      (data)=>{
        console.log(data);
        this.tharapist=data;
        this.formTherapist.reset();
      },
      (error) => {
        console.error('Error creating therapist:', error);
      }
    );
  }
NavigateToTherapists(){
  this.router.navigate(['/user/needHelp']);
}
}
