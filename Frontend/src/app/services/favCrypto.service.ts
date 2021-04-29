import {Injectable} from '@angular/core';
import {CryptocurrencyModel} from '../models/Cryptocurrency.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

const BASE_URL = '/api/cryptocurrencies/favCryptocurrency/'

@Injectable({ providedIn: 'root' })
export class FavCryptocurrenciesService{
  constructor(private httpClient: HttpClient) {}

  getCryptocurrencies(): Observable<CryptocurrencyModel[]>{
    return this.httpClient.get(BASE_URL).pipe() as Observable<CryptocurrencyModel[]>;
  }

}
