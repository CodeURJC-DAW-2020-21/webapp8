import { Component, OnInit } from '@angular/core';
import { EntryModel } from 'src/app/models/Entry.model';
import { EntriesService } from '../../services/entries.service'

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {

  entries: EntryModel[] = [];

  constructor(private entriesService: EntriesService) { }

  ngOnInit(): void {
    this.getEntries(0);
  }

  getEntries(page: number) {
    this.entries = [];
    this.entriesService.getEntries(0).subscribe(
      entries => this.entries = entries
    );
    
  }
}