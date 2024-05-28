import { TestBed } from '@angular/core/testing';

import { TherapieSessionService } from './therapie-session.service';

describe('TherapieSessionService', () => {
  let service: TherapieSessionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TherapieSessionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
