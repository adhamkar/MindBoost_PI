import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Injectable } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Injectable()
export class AuthenticationGuard implements CanActivate {

  constructor(private authService: AuthService,private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log('Checking if user is authenticated');
    if (this.authService.isAuthtenticated) {
      console.log('User is authenticated, allowing navigation');
      return true;
    } else {
      console.log('User is not authenticated, redirecting to /login');
      this.router.navigateByUrl('/login')
      return false;
    }
  }
}
