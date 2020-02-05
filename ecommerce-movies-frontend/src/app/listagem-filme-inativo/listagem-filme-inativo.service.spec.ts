import { TestBed } from '@angular/core/testing';

import { ListagemFilmeInativoService } from './listagem-filme-inativo.service';

describe('ListagemFilmeInativoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListagemFilmeInativoService = TestBed.get(ListagemFilmeInativoService);
    expect(service).toBeTruthy();
  });
});
