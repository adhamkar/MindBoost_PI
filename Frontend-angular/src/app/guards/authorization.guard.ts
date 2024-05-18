  import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
  import { Injectable } from '@angular/core';
  import {AuthService} from "../services/auth.service";

  @Injectable()
  export class AuthorizationGuard implements CanActivate {

    constructor(private authService: AuthService,private router: Router) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
      if(this.authService.roles.includes('ADMIN')){

        return true;
      }
      return false;
    }
  }
