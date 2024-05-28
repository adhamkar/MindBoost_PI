import { Injectable } from '@angular/core';
import {Patient} from "../models/patient.model";
import {Observable} from "rxjs";
import {Therapist} from "../models/therapist.model";
import {TherapieSession} from "../models/therapie-session.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TherapieSessionService {

  private baseUrl = 'http://localhost:8085/sessions';

  public AddPatientSession(session: TherapieSession, patientName: string, therapistId: number): Observable<TherapieSession> {
    return this.http
      .post<TherapieSession>(`${this.baseUrl}/${patientName}/${therapistId}/mysession`, session);
  }

  public getTherapistSessions(name:any){
    return this.http.get<TherapieSession[]>(`${this.baseUrl}/therapist/${name}`);
  }
  constructor(private http: HttpClient) { }

  public getAllSessions(): Observable<TherapieSession[]> {
    return this.http.get<TherapieSession[]>(this.baseUrl);
  }
  getSessionsByPatient(patientId: string): Observable<TherapieSession[]> {
    return this.http.get<TherapieSession[]>(`${this.baseUrl}/Patient/${patientId}`);
  }

  public getSession(id: number): Observable<TherapieSession> {
    return this.http.get<TherapieSession>(`${this.baseUrl}/${id}`);
  }

  public searchSessionByName(sessionName: string): Observable<TherapieSession[]> {
    return this.http.get<TherapieSession[]>(`${this.baseUrl}/name/${sessionName}`);
  }


  public getTherapist(id: number): Observable<Therapist> {
    return this.http.get<Therapist>(`${this.baseUrl}/${id}/therapist`);
  }

  public getPatient(id: number): Observable<Patient> {
    return this.http.get<Patient>(`${this.baseUrl}/${id}/patient`);
  }


  public addSession(session: TherapieSession, patientId: number, therapistId: number): Observable<TherapieSession> {
    return this.http.post<TherapieSession>(`${this.baseUrl}/${patientId}/${therapistId}`, session);
  }

  public updateSession(id: number, session: TherapieSession): Observable<TherapieSession> {
    return this.http.patch<TherapieSession>(`${this.baseUrl}/${id}`, session);
  }

  public deleteSession(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

