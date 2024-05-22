/*
import { TestBed } from '@angular/core/testing';
import { HTTP_INTERCEPTORS, HttpClient, HttpHandler, HttpRequest } from '@angular/common/http';
import { AppHttpInterceptor } from './app-http.interceptor';
import { AuthService } from '../services/auth.service';

describe('AppHttpInterceptor', () => {
  let interceptor: AppHttpInterceptor;
  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        AuthService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AppHttpInterceptor,
          multi: true
        }
      ]
    });

    authService = TestBed.inject(AuthService);
    interceptor = new AppHttpInterceptor(authService);
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });

  // Additional tests for the interceptor can go here
});
*/
