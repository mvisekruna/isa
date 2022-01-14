import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationHomeDetailsComponent } from './vacation-home-details.component';

describe('VacationHomeDetailsComponent', () => {
  let component: VacationHomeDetailsComponent;
  let fixture: ComponentFixture<VacationHomeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationHomeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationHomeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
