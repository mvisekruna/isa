import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationForOthersFormComponent } from './registration-for-others-form.component';

describe('RegistrationForOthersFormComponent', () => {
  let component: RegistrationForOthersFormComponent;
  let fixture: ComponentFixture<RegistrationForOthersFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationForOthersFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationForOthersFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
