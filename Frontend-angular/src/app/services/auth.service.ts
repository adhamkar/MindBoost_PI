import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {jwtDecode} from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isAuthtenticated: boolean=false;
  roles:any;
  username:any;
  accessToken!:string;
  constructor(private http:HttpClient) { }

  public login(username:string,password:string){
    let params=new HttpParams().set("username",username).set("password",password);
    let options={
      headers:
       new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    }
    return this.http.post("http://localhost:8085/auth/login",params,options)
  }

  LoadProfile(data: any) {
    this.isAuthtenticated=true;
    this.accessToken=data['access-token'];
    let decodedJwt:any=jwtDecode(this.accessToken);
    this.username=decodedJwt.sub;
    this.roles=decodedJwt.scope;

  }
}
