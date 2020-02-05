import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteFilmeComponent } from '../delete-filme/delete-filme.component';

describe('DeleteFilmeComponent', () => {
  let component: DeleteFilmeComponent;
  let fixture: ComponentFixture<DeleteFilmeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteFilmeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteFilmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
