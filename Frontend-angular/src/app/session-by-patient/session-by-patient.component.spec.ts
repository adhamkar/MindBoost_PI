import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SessionByPatientComponent } from './session-by-patient.component';

describe('SessionByPatientComponent', () => {
  let component: SessionByPatientComponent;
  let fixture: ComponentFixture<SessionByPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SessionByPatientComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SessionByPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
