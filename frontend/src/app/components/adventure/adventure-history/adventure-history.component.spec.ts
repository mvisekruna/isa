import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureHistoryComponent } from './adventure-history.component';

describe('AdventureHistoryComponent', () => {
  let component: AdventureHistoryComponent;
  let fixture: ComponentFixture<AdventureHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
