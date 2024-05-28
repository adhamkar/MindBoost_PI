import {Component, OnInit} from '@angular/core';
import {TherapieSession} from "../models/therapie-session.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TherapieSessionService} from "../services/therapie-session.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Patient} from "../models/patient.model";
import {Therapist} from "../models/therapist.model";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-reservation-session',
  templateUrl: './reservation-session.component.html',
  styleUrl: './reservation-session.component.css'
})
export class ReservationSessionComponent implements OnInit{
  sessionForm!: FormGroup;
  isDarkMode = false;
  therapistId!: number;
  patientName!: string;
newSession:any;
  constructor(
    private fb: FormBuilder,
    private sessionService: TherapieSessionService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.therapistId = Number(this.route.snapshot.paramMap.get('therapistId'));
    this.patientName = this.authService.getUserId().username;

    this.sessionForm = this.fb.group({
      nameSession: ['', Validators.required],
      dateSession: ['', Validators.required]
      /*patientId: ['', Validators.required],
      therapistId: ['', Validators.required]*/
    });
  }

 /* onSubmit(): void {
    if (this.sessionForm.valid) {
      const session: TherapieSession = {
        id: 0,
        nameSession: this.sessionForm.value.nameSession,
        dateSession: new Date(this.sessionForm.value.dateSession),

      };

      this.sessionService.addSession(session, session.patient.id, session.therapist.id).subscribe(
        (response: TherapieSession) => {
          console.log('Session added successfully', response);
          this.router.navigate(['/sessions']);
        },
        (error: any) => {
          console.error('Error adding session', error);
        }
      );
    }
  }*/
  AddSession(){
    this.newSession=this.sessionForm.value;
    this.sessionService.AddPatientSession(this.newSession,this.authService.getUserId(),this.therapistId).subscribe(
      data=>{
        this.newSession=data;
        console.log(data);
        this.NavigateToSessions()
        this.sessionForm.reset();
      },
      error => {
        console.log(error);
      }
    )
  }
NavigateToSessions(){
  this.router.navigate(['user/Mysession']);
}
  toggleTheme() {
    this.isDarkMode = !this.isDarkMode;
  }
}
