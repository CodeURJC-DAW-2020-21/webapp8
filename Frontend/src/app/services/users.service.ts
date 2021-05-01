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

  getUserById(idUser: number): Observable<UserModel> {
    return this.httpClient.get(BASE_URL + idUser).pipe() as Observable<UserModel>;
  }

  getUserByIdEntries(idEntry: number): Observable<UserModel>{
    let url = idEntry + '/entries/users';
    return this.httpClient.get(BASE_URL + url).pipe() as Observable<UserModel>;
  }
  // getImageByUserID(idUser: number): Observable<Blob>{
  //   let url = idUser + '/image';
  //   return this.httpClient.get(BASE_URL +  url, {responseType: "blob"}).pipe() as Observable<Blob>;
  // }

  getImageByUserID(user: UserModel): string{
    if (user.idUser){
      return "/api/users/" + user.idUser + "/image";
    }
  }

  deleteUserById(idUser: number): Observable<any> {
    let url = idUser;
    return this.httpClient.delete(BASE_URL + url).pipe() as Observable<any>;
  }

  createUser(firstname: string, surname: string, email: string, name: string, encodedPassword: string): Observable<UserModel> {
    return this.httpClient.post(BASE_URL, {firstname, surname, email, name, encodedPassword}).pipe() as Observable<UserModel>;
  }

  sendEmail(email: string): Observable<Response> {
    let url = "password"
    return this.httpClient.post(BASE_URL + url, {email}).pipe() as Observable<Response>;
  }

  patchUser(firstname: string, surname: string): Observable<UserModel> {
    return this.httpClient.patch(BASE_URL, {firstname, surname}).pipe() as Observable<UserModel>;
  }

  postImage(idUser: number, formData: FormData): Observable<any>{
    let url = idUser + '/image/';
    return this.httpClient.post(BASE_URL + url, formData).pipe() as Observable<any>;
  }
}
