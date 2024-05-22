

import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "../services/auth.service";

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) { }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("Intercepted request", req.url);
    if (!req.url.includes("/auth/login") && !req.url.includes("/auth/signup")) {
      let newReq = req.clone({
        headers: req.headers.set("Authorization", "Bearer " + this.authService.accessToken)
      });
      return next.handle(newReq);
    } else {
      return next.handle(req);
    }

  }
}


