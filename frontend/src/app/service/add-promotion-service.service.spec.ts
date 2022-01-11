import { TestBed } from '@angular/core/testing';

import { AddPromotionServiceService } from './add-promotion-service.service';

describe('AddPromotionServiceService', () => {
  let service: AddPromotionServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddPromotionServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
