import { TestBed } from '@angular/core/testing';

import { VacationHomeServiceService } from './vacation-home-service.service';

describe('VacationHomeServiceService', () => {
  let service: VacationHomeServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VacationHomeServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
