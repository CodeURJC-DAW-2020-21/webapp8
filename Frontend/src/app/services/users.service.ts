import { map } from 'rxjs/operators'
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from '../models/User.model';

const BASE_URL = 'api/users/';

@Injectable({ providedIn: 'root' })
export class UsersService {

  users: UserModel[] = [];
  constructor(private httpClient: HttpClient) { }

  getUsers(): Observable<UserModel[]>{
    return this.httpClient.get(BASE_URL).pipe() as Observable<UserModel[]>;
  }
  getUserByIdEntries(idEntry: number): Observable<UserModel>{
    let url = idEntry + '/entries/users';
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<UserModel>;
  }
  getImageByUserID(idUser: number): Observable<Blob>{
    let url = idUser + '/image';
    return this.httpClient.get(BASE_URL +  url, {responseType: "blob"}).pipe() as Observable<Blob>;
  }

  deleteUserById(idUser: number): Observable<any> {
    let url = idUser;
    return this.httpClient.delete(BASE_URL + url).pipe() as Observable<any>;
  }
}
