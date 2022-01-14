import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatHistoryComponent } from './boat-history.component';

describe('BoatHistoryComponent', () => {
  let component: BoatHistoryComponent;
  let fixture: ComponentFixture<BoatHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
