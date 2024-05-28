import { Component } from '@angular/core';
import {Therapist} from "../models/therapist.model";
import {TherapistService} from "../services/therapist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-need-help',
  templateUrl: './need-help.component.html',
  styleUrl: './need-help.component.css'
})
export class NeedHelpComponent {
  therapists: any;

  constructor(private therapistService: TherapistService,private router:Router) { }

  ngOnInit(): void {
    this.therapistService.getAllTherapists().subscribe(data => {
      console.log(data);
      this.therapists = data;
    });
  }

  navigateToReservation() {
    this.router.navigate(['/user/Reservation']);

  }

}
