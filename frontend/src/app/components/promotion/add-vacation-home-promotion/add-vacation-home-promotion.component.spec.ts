import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVacationHomePromotionComponent } from './add-vacation-home-promotion.component';

describe('AddVacationHomePromotionComponent', () => {
  let component: AddVacationHomePromotionComponent;
  let fixture: ComponentFixture<AddVacationHomePromotionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddVacationHomePromotionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddVacationHomePromotionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
