import {Component, OnInit} from '@angular/core';
import {TherapieSession} from "../models/therapie-session.model";
import {TherapieSessionService} from "../services/therapie-session.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-session-by-patient',
  templateUrl: './session-by-patient.component.html',
  styleUrl: './session-by-patient.component.css'
})
export class SessionByPatientComponent implements OnInit {
  sessions: TherapieSession[] = [];
  patientId!: string

  constructor(private sessionservice: TherapieSessionService, public authService: AuthService) {

  }

  ngOnInit(): void {
    this.patientId = this.authService.getUserId();
    this.getSessionsByPatient(this.patientId);
  }

  getSessionsByPatient(patientId: string): void {
    this.sessionservice.getSessionsByPatient(patientId)
      .subscribe(
        (data: TherapieSession[]) => {
          console.log(data);
          this.sessions = data;
        },
        (error) => {
          console.error('Error fetching therapy sessions', error);
        }
      );

  }
}
