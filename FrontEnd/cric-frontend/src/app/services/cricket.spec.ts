import { TestBed } from '@angular/core/testing';

import { Cricket } from './cricket';

describe('Cricket', () => {
  let service: Cricket;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Cricket);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
