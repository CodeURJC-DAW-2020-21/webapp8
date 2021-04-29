import { Component, OnInit } from '@angular/core';
import { CryptocurrencyModel } from 'src/app/models/Cryptocurrency.model';
import { UserModel } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { CryptocurrenciesService } from '../../services/cryptocurrencies.service'
import {FavCryptocurrenciesService} from '../../services/favCrypto.service';


@Component({
  selector: 'app-cryptocurrency',
  templateUrl: './cryptocurrency.component.html',
  styleUrls: ['./cryptocurrency.component.css']
})
export class CryptocurrencyComponent implements OnInit {

  cryptocurrencies: CryptocurrencyModel[];
  cryptocurrenciesFav: CryptocurrencyModel[];
  friendsRecommended: UserModel[];
  recommendedCryptocurrencies: CryptocurrencyModel[];

  constructor(private cryptocurrenciesService: CryptocurrenciesService, public loginService: LoginService, private favCryptocurrenciesService: FavCryptocurrenciesService) { }

  ngOnInit(): void {
    this.getCryptocurrencies();
    this.getFriendsRecommended();
    this.getFavCryptocurrencies();
  }

  refresh(): void {
    this.getFavCryptocurrencies();
    this.getCryptocurrencies();
  }

  getCryptocurrencies(){
    this.cryptocurrencies = [];
    this.cryptocurrenciesService.getCryptocurrencies().subscribe(
      cryptocurrencies => this.cryptocurrencies = cryptocurrencies
    );
  }

  getFavCryptocurrencies(){
    this.cryptocurrenciesFav = [];
    this.favCryptocurrenciesService.getCryptocurrencies().subscribe(
      cryptocurrenciesFav => this.cryptocurrenciesFav = cryptocurrenciesFav
    )
  }

  changeImage(cryptocurrency2: CryptocurrencyModel, idUser: number){
    if(!this.isAdded(cryptocurrency2)){
      this.cryptocurrenciesService.postFavCryptocurrency(cryptocurrency2).subscribe(
        response => console.log(response),
        error => console.log(error)
      );
      this.refresh();
    }
    else{
      this.cryptocurrenciesService.deleteFavCryptocurrency(cryptocurrency2).subscribe( 
        response => console.log(response),
        error => console.log(error)
      );
      this.refresh();
    }
    this.refresh();
  }

  isAdded(cryptocurrencyAdded:CryptocurrencyModel): boolean{
    let added: boolean
    this.cryptocurrenciesFav.forEach(function(favCryptocurrency){
      if(favCryptocurrency.nameCripto===cryptocurrencyAdded.nameCripto){
        added = true;
      }
    });
    return added;
  }

  getFriendsRecommended(){
    this.friendsRecommended = [];
    this.cryptocurrenciesService.getFriendsRecommended().subscribe(
      friendsRecommended => this.friendsRecommended = friendsRecommended
    );
  }

  // getRecommendedCryptocurrencies(){
  //   this.friendsRecommended.forEach(function(friend){

  //   })
    
  // }
}
