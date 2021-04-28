import { Component, OnInit } from '@angular/core';
import { CryptocurrencyModel } from 'src/app/models/Cryptocurrency.model';
import { UserModel } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { CryptocurrenciesService } from '../../services/cryptocurrencies.service'


@Component({
  selector: 'app-cryptocurrency',
  templateUrl: './cryptocurrency.component.html',
  styleUrls: ['./cryptocurrency.component.css']
})
export class CryptocurrencyComponent implements OnInit {

  cryptocurrencies: CryptocurrencyModel[];
  friendsRecommended: UserModel[];
  // image: string = "images/starEmpty.svg";

  constructor(private cryptocurrenciesService: CryptocurrenciesService, public loginService: LoginService) { }

  ngOnInit(): void {
    this.getCryptocurrencies();
  }

  getCryptocurrencies(){
    this.cryptocurrencies = [];
    this.cryptocurrenciesService.getCryptocurrencies().subscribe(
      cryptocurrencies => this.cryptocurrencies = cryptocurrencies
    );
  }

  getUrlImage(cryptocurrency: CryptocurrencyModel): string{
    return "assets/" + cryptocurrency.image;
  }

  changeImage(cryptocurrency2: CryptocurrencyModel){
    if(cryptocurrency2.image === "images/starEmpty.svg"){
      cryptocurrency2.image = "images/star.svg"
    }
    else{
      cryptocurrency2.image = "images/starEmpty.svg"
    }
  }

  getFriendsRecommended(){
    this.friendsRecommended = [];
    this.cryptocurrenciesService.getFriendsRecommended().subscribe(
      friendsRecommended => this.friendsRecommended = friendsRecommended
    );

  }
}
