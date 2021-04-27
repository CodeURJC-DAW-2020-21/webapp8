import { map } from 'rxjs/operators'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntryModel } from '../models/Entry.model';
import { UserModel } from '../models/User.model';

const BASE_URL = "/api/entries";

@Injectable({ providedIn: 'root' })
export class EntriesService {

  entries: EntryModel[] = [];
  constructor(private httpClient: HttpClient) { }
  
  getEntries(page: number): Observable<EntryModel[]>{
    let url = "/entry/users/" + "?numOfPage=" + page.toString() + "";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<EntryModel[]>;
  }

  getUserByIdEntry(idEntry: number): Observable<UserModel>{
    let url = idEntry.toString() + "/user";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<UserModel>;
  }
}