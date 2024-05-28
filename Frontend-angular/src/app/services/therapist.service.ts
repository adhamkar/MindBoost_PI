import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Therapist} from "../models/therapist.model";
import {Post} from "../models/post.model";

@Injectable({
  providedIn: 'root'
})
export class TherapistService {

  private baseUrl = 'http://localhost:8085/therapistes';

  constructor(private http: HttpClient) { }

  public getAllTherapists(): Observable<Therapist[]> {
    return this.http.get<Therapist[]>(`${this.baseUrl}`);
  }

  public getTherapist(id: number): Observable<Therapist> {
    return this.http.get<Therapist>(`${this.baseUrl}/${id}`);
  }

  public addTherapist(therapist: Therapist): Observable<Therapist> {
    return this.http.post<Therapist>(this.baseUrl, therapist);
  }

  public deleteTherapist(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  public updateTherapist(id: number, therapist: Therapist): Observable<Therapist> {
    return this.http.patch<Therapist>(`${this.baseUrl}/${id}`, therapist);
  }

  public getTherapistPosts(id: number): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.baseUrl}/${id}/posts`);
  }
}
