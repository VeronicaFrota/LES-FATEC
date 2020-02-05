import { TestBed } from '@angular/core/testing';

import { AtivarFilmeService } from './ativar-filme.service';

describe('AtivarFilmeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AtivarFilmeService = TestBed.get(AtivarFilmeService);
    expect(service).toBeTruthy();
  });
});
