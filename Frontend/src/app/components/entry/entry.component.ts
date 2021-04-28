import { Component, EventEmitter, OnInit, Output } from '@angular/core';
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
  user: UserModel;
  image: Blob;
  isImageLoading: boolean;
  public isCollapsed;

  constructor(private entriesService: EntriesService, private usersService: UsersService) { }

  ngOnInit(): void {
    this.isCollapsed = true;
    this.getEntries(2);
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
  // collapseDiv(id: number): void{
  //   let elem = document.getElementById('divToCollapse' + id);
  //   elem.setAttribute('ngbCollapse', "false");
  // }
}
