import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBoatPromotionComponent } from './add-boat-promotion.component';

describe('AddBoatPromotionComponent', () => {
  let component: AddBoatPromotionComponent;
  let fixture: ComponentFixture<AddBoatPromotionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBoatPromotionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBoatPromotionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
