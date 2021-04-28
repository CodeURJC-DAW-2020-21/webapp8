import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FavCryptoComponent } from './fav-crypto.component';

describe('FavCryptoComponent', () => {
  let component: FavCryptoComponent;
  let fixture: ComponentFixture<FavCryptoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FavCryptoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FavCryptoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
