import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VacationHomeListComponent } from './vacation-home-list.component';

describe('VacationHomeListComponent', () => {
  let component: VacationHomeListComponent;
  let fixture: ComponentFixture<VacationHomeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VacationHomeListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VacationHomeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
