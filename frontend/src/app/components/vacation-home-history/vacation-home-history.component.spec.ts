import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationHomeHistoryComponent } from './vacation-home-history.component';

describe('VacationHomeHistoryComponent', () => {
  let component: VacationHomeHistoryComponent;
  let fixture: ComponentFixture<VacationHomeHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationHomeHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationHomeHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
