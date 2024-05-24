import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private Url = 'http://localhost:8085';
  profileData: any = {
    name :'maryam nfad',
    role:'patient',
    gender: 'Male',
    phone: '1234567890',
    email: 'example@example.com',
    age: 30,
    profession: 'Engineer',
    medicalHistory: 'No medical history',
    city: 'New York'
  };

  constructor(private http: HttpClient) { }

  getProfileData(): any {
    return this.profileData;
  }
///patients/profile/{name}
getProfile(name:any): Observable<Profile> {
  return this.http.get<Profile>(`${this.Url}`+"/patients/profile/"+name);
}
  updateProfileData(newData: any): void {
    this.profileData = newData;
  }
}
export interface Profile {
  id: number;
  role: string;
  userName: string;
  phone: string;
  email: string
  age: number;
  gender: string;
  profession: string;
  city: string;
  medicalHistory: string;
}
