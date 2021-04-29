import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from '../models/User.model';

const BASE_URL = '/api/users/friends'

@Injectable({ providedIn: 'root' })
export class FriendsService{
    constructor(private httpClient: HttpClient) {}

    getFriends(): Observable<UserModel[]>{
        return this.httpClient.get(BASE_URL).pipe() as Observable<UserModel[]>;
    }

    postFriend(idUser){
        return this.httpClient.post(BASE_URL + "/" + idUser, idUser).pipe() as Observable<UserModel[]>;
    }

    deleteFriend(idUser){
        return this.httpClient.delete(BASE_URL + "/" + idUser).pipe() as Observable<UserModel[]>;
    }
}