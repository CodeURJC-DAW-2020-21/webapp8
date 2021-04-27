import { Component, OnInit } from '@angular/core';
import { EntryModel } from 'src/app/models/Entry.model';
import { UserModel } from 'src/app/models/User.model';
import { EntriesService } from '../../services/entries.service'
import { UsersService } from '../../services/users.service'

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {

  entries: EntryModel[];
  moreEntries: EntryModel[];
  user: UserModel;
  image: Blob;
  isImageLoading: boolean;
  imageToShow: any;

  constructor(private entriesService: EntriesService, private usersService: UsersService) { }

  ngOnInit(): void {
    this.getEntries(0);
  }

  getEntries(page: number) {
    this.entries = [];
    this.entriesService.getEntries(0).subscribe(
      data => {
        this.entries = data,
        this.entries.forEach(function(entry){  
          this.getImageByUserID(entry.user.idUser);
          entry.user.image = this.imageToShow;
         })
      }
    );
  }
  
  getUserByIdEntry(idEntry: number) {
    this.user = null;
    this.entriesService.getUserByIdEntry(idEntry).subscribe(
      user => this.user = user
    );
    return this.user;
  }

  getImageByUserID(idUser: number){
    return "/api/users/" + idUser + "/image";
  }
}