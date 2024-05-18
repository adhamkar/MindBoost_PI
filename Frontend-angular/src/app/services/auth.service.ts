import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";
import {Patient} from "../interfaces/patient";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthtenticated: boolean=false;
  roles:any;
  username:any;
  accessToken!:any;
  patient:Patient[]=[];
  constructor(private http:HttpClient,private router:Router) { }

  public login(username:string,password:string){
    let params=new HttpParams().set("username",username).set("password",password);
    let options={
      headers:
       new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    }
    return this.http.post("http://localhost:8085/auth/login",params,options)
  }
  public signup(patient:Patient): Observable<Patient>{
    console.log('Signing up patient:', patient);
    let options={
      headers: new HttpHeaders().set("Content-Type", "application/json")
    }
    return this.http.post<Patient>("http://localhost:8085/auth/signup",patient,options)

  }

  LoadProfile(data: any) {
    this.isAuthtenticated=true;
    this.accessToken=data['access-token'];
    let decodedJwt:any=jwtDecode(this.accessToken);
    this.username=decodedJwt.sub;
    this.roles=decodedJwt.scope;
    window.localStorage.setItem("jwt-Token",this.accessToken);
  }

  logout(){
    this.isAuthtenticated=false;
    this.accessToken=undefined;
    this.username=undefined;
    this.roles=undefined;
    //window.localStorage.removeItem("jwt-Token");
    this.router.navigateByUrl("/login");
  }
  LoadJwtTokenFromLocalStorage(){
    let token=this.accessToken=window.localStorage.getItem("jwt-Token");
    if(token){
     this.LoadProfile({"access-Token":token})
      this.router.navigateByUrl("/user/posts");
    }
  }


}
