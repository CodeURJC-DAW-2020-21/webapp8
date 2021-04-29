import { Component, EventEmitter, OnChanges, OnInit, Output } from '@angular/core';
import { EntryModel } from 'src/app/models/Entry.model';
import { UserModel } from 'src/app/models/User.model';
import { EntriesService } from '../../services/entries.service'
import { UsersService } from '../../services/users.service'

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit, OnChanges {

  entries: EntryModel[];
  user: UserModel;
  image: Blob;
  isImageLoading: boolean;
  public isCollapsed;
  public pageToFind: number;
  public showBtn: string;

  constructor(private entriesService: EntriesService, private usersService: UsersService) { }

  ngOnInit(): void {
    this.isCollapsed = true;
    this.pageToFind = 0;
    this.getEntries(this.pageToFind);
    this.showBtn = '';
  }

  ngOnChanges(): void {
    this.isCollapsed = true;
    this.getEntries(0);
  }

  getEntries(page: number): void {
    this.entries = [];
    this.entriesService.getEntries(page).subscribe(
      entries => this.entries = entries
    );
  }

  getUserByIdEntry(idEntry: number): UserModel {
    this.user = null;
    this.entriesService.getUserByIdEntry(idEntry).subscribe(
      user => this.user = user
    );
    return this.user;
  }

  ajaxGetMore(page) {
    this.pageToFind = page + 1;
    this.entriesService.getEntries(this.pageToFind).subscribe(
      response => {
        let data: any = response;
        let sizePage = data.length;
        if (sizePage === 5){
          this.showBtn = '';
        } else {
          this.showBtn = 'display: none';
        }
        for (var i = 0; i < sizePage; i++) {
          let newEntry = data[i];
          if (newEntry.idEntry === 1){
            this.showBtn = 'display: none';
          }
          this.entries.push(newEntry);
        }
      },
      error => console.log(error) //this.showBtn = 'display: none'        
    );
  }
}
