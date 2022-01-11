import { TestBed } from '@angular/core/testing';

import { AdventureServiceService } from './adventure-service.service';

describe('AdventureServiceService', () => {
  let service: AdventureServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdventureServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
