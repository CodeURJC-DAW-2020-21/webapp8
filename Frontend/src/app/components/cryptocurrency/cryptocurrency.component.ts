import { Component, OnInit } from '@angular/core';
import { CryptocurrencyModel } from 'src/app/models/Cryptocurrency.model';
import { CryptocurrenciesService } from '../../services/cryptocurrencies.service'

@Component({
  selector: 'app-cryptocurrency',
  templateUrl: './cryptocurrency.component.html',
  styleUrls: ['./cryptocurrency.component.css']
})
export class CryptocurrencyComponent implements OnInit {

  cryptocurrencies: CryptocurrencyModel[];

  constructor(private cryptocurrenciesService: CryptocurrenciesService) { }

  ngOnInit(): void {
    this.getCryptocurrencies();
  }

  getCryptocurrencies(){
    this.cryptocurrencies = [];
    this.cryptocurrenciesService.getCryptocurrencies().subscribe(
      cryptocurrencies => this.cryptocurrencies = cryptocurrencies
    );
  }

  getUrlImage(path: string): string{
    return "assets/" + path;
  }
}
