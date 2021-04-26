import { map } from 'rxjs/operators'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntryModel } from '../models/Entry.model';

const BASE_URL = "/api/entries/";

@Injectable({ providedIn: 'root' })
export class EntriesService {

  constructor(private httpClient: HttpClient) { }
  
  entries: EntryModel[] = []
  getEntries(page: number): Observable<EntryModel[]>{
    let url = "?numOfPage=" + page + "";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<EntryModel[]>;
  }
}