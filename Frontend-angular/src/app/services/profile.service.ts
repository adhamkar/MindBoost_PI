import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
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

  constructor() { }

  getProfileData(): any {
    return this.profileData;
  }

  updateProfileData(newData: any): void {
    this.profileData = newData;
  }
}
