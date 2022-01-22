import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageVacationHomeOwnerComponent } from './home-page-vacation-home-owner.component';

describe('HomePageVacationHomeOwnerComponent', () => {
  let component: HomePageVacationHomeOwnerComponent;
  let fixture: ComponentFixture<HomePageVacationHomeOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageVacationHomeOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageVacationHomeOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
