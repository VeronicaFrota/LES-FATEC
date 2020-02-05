import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListagemFilmeInativoComponent } from './listagem-filme-inativo.component';

describe('ListagemFilmeInativoComponent', () => {
  let component: ListagemFilmeInativoComponent;
  let fixture: ComponentFixture<ListagemFilmeInativoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListagemFilmeInativoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListagemFilmeInativoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
