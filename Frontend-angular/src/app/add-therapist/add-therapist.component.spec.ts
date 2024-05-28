import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTherapistComponent } from './add-therapist.component';

describe('AddTherapistComponent', () => {
  let component: AddTherapistComponent;
  let fixture: ComponentFixture<AddTherapistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddTherapistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddTherapistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
