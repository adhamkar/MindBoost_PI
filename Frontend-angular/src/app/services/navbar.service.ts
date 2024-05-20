import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  private isProfilePageSubject = new BehaviorSubject<boolean>(false);
  isProfilePage$ = this.isProfilePageSubject.asObservable();

  setProfilePage(isProfilePage: boolean) {
    this.isProfilePageSubject.next(isProfilePage);
  }
  constructor() { }
}
