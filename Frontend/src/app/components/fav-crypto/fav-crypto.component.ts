import { Component, OnInit } from '@angular/core';
import {FavCryptocurrenciesService} from '../../services/favCrypto.service';
import {CryptocurrencyModel} from '../../models/Cryptocurrency.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-fav-crypto',
  templateUrl: './fav-crypto.component.html',
  styleUrls: ['./fav-crypto.component.css']
})
export class FavCryptoComponent implements OnInit {

  favCryptocurrencies: CryptocurrencyModel[];

  constructor(private favcryptocurrenciesService: FavCryptocurrenciesService, public loginService: LoginService) { }

  ngOnInit(): void {
    this.getCryptocurrencies();
  }

  getCryptocurrencies(){
    this.favCryptocurrencies = [];
    this.favcryptocurrenciesService.getCryptocurrencies().subscribe(
      cryptocurrencies => this.favCryptocurrencies = cryptocurrencies
    );
  }

  getUrlImage(path: string): string{
    return 'assets/' + path;
  }

}
