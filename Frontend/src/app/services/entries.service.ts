import { LoginService } from './login.service';
import { map } from 'rxjs/operators'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntryModel } from '../models/Entry.model';
import { UserModel } from '../models/User.model';
import { CommentModel } from '../models/Comment.model';

const BASE_URL = "/api/entries/";

@Injectable({ providedIn: 'root' })
export class EntriesService {

  public entries: EntryModel[] = [];

  constructor(private httpClient: HttpClient, public loginService: LoginService) { }

  getEntries(page: number): void{
    this.entries = []
    this.getEntriesFromApi(page).subscribe(
      response => {
        let data: any = response;
        for (var i = 0; i < data.length; i++) {
          let newEntry = data[i];
          this.entries.push(newEntry);
        }
      }
    );
  }

  getEntriesFromApi(page: number): Observable<EntryModel[]>{
    let url = "entry/users/" + "?numOfPage=" + page.toString() + "";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<EntryModel[]>;
  }

  getUserByIdEntry(idEntry: number): Observable<UserModel>{
    let url = idEntry + "/user";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<UserModel>;
  }

  getCommentsByIdEntry(idEntry: number): Observable<EntryModel[]>{
    let url = idEntry + "/comments";
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<EntryModel[]>;
  }

  postEntry(title: string, description: string): Observable<EntryModel> {
    let url = this.loginService.currentUser().idUser;
    let entriesToReturn = this.httpClient.post(BASE_URL + url, {title, description}).pipe() as Observable<EntryModel>;
    return entriesToReturn;
  }

  pushEntry(newEntry: EntryModel): void{
    this.entries.push(newEntry);
  }
}
