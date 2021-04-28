import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CryptocurrencyModel} from '../models/Cryptocurrency.model';
import { UserModel } from '../models/User.model';

const BASE_URL = '/api/cryptocurrencies/'

@Injectable({ providedIn: 'root' })
export class CryptocurrenciesService{
    cryptocurrencies: CryptocurrencyModel[] = []
    constructor(private httpClient: HttpClient) {}

    getCryptocurrencies(): Observable<CryptocurrencyModel[]>{
        return this.httpClient.get(BASE_URL).pipe() as Observable<CryptocurrencyModel[]>;
    }

    getFriendsRecommended(idUser: number): Observable<UserModel[]>{
        return this.httpClient.get('/api/users/'+idUser+'/recommended').pipe() as Observable<UserModel[]>;
    }

    postFavCryptocurrency(cryptocurrency: CryptocurrencyModel, idUser: number){
        return this.httpClient.post(BASE_URL + idUser +'/favCryptocurrency', cryptocurrency).pipe() as Observable<UserModel[]>;
    }
}