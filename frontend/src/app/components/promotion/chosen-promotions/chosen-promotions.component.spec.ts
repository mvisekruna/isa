import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChosenPromotionsComponent } from './chosen-promotions.component';

describe('ChosenPromotionsComponent', () => {
  let component: ChosenPromotionsComponent;
  let fixture: ComponentFixture<ChosenPromotionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChosenPromotionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChosenPromotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
