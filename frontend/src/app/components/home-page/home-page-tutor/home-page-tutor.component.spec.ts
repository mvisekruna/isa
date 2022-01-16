import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageTutorComponent } from './home-page-tutor.component';

describe('HomePageTutorComponent', () => {
  let component: HomePageTutorComponent;
  let fixture: ComponentFixture<HomePageTutorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePageTutorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePageTutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
