import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AtivarFilmeComponent } from './ativar-filme.component';

describe('AtivarFilmeComponent', () => {
  let component: AtivarFilmeComponent;
  let fixture: ComponentFixture<AtivarFilmeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AtivarFilmeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AtivarFilmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
