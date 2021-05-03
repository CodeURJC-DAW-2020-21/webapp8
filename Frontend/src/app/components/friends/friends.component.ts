import { UserModel } from './../../models/User.model';
import { Component, Input, OnInit, OnChanges } from '@angular/core';
import {FriendsService} from '../../services/friends.service';
import { EntriesService } from '../../services/entries.service';
import { EntryModel } from 'src/app/models/Entry.model';
import { LoginService } from 'src/app/services/login.service';
import { UsersService } from './../../services/users.service';


@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit{
  @Input()
  userEntry: UserModel;
  id: number;
  actualUserID: number;
  isUserVar: boolean = false;


  constructor(private friendsService: FriendsService, public entriesService: EntriesService, public loginService: LoginService) {
   }

  ngOnInit(): void {
  }

  isUser(): boolean{
    this.getActualUser();
    if(!(this.loginService.user.name === this.userEntry.name)){
      this.isUserVar = true;
    }
    return this.isUserVar;
  }

  refresh(): void {
    this.entriesService.getEntries(0);
  }

  getActualUser() {
    this.actualUserID = this.loginService.currentUser().idUser
  }

  isFriendAdded(): boolean{
    let added: boolean = false;
    let userPrueba: UserModel = this.userEntry;
    if(this.loginService.currentUser().friends){
      this.loginService.currentUser().friends.forEach(function(friend){
        if(userPrueba.name === friend.name){
          added = true;
        }
      });
      }
    return added;
  }

  addOrDeleteFriend(){
    if(!this.isFriendAdded()){
      this.friendsService.postFriend(this.userEntry.idUser).subscribe(
        response => {
          let data: any = response;
          this.entriesService.getEntries(0);
        },
        error => console.log(error)
      );
    }
    else{
      this.friendsService.deleteFriend(this.userEntry.idUser).subscribe(
        response => {
          let data: any = response;
          this.entriesService.getEntries(0);
        }, 
        error => console.log(error)
      );
    }
  }
}
