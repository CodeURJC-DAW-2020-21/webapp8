import { UserModel } from '../models/User.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const BASE_URL = '/api/auth';

@Injectable({ providedIn: 'root' })
export class LoginService {

    logged: boolean;
    user: UserModel;

    constructor(private http: HttpClient) {
        this.reqIsLogged();
    }

    reqIsLogged() {

        this.http.get('/api/users/me', { withCredentials: true }).subscribe(
            response => {
                this.user = response as UserModel;
                this.logged = true;
            },
            error => {
                if (error.status != 404) {
                    console.error('Error when asking if logged: ' + JSON.stringify(error));
                }
            }
        );

    }

    logIn(user: string, pass: string) {

        this.http.post(BASE_URL + "/login", { username: user, password: pass }, { withCredentials: true })
            .subscribe(
                (response) => this.reqIsLogged(),
                (error) => alert("Wrong credentials")
            );

    }

    logOut() {

        return this.http.post(BASE_URL + '/logout', { withCredentials: true })
            .subscribe((resp: any) => {
                console.log("LOGOUT: Successfully");
                this.logged = false;
                this.user = undefined;
            });

    }

    isLogged() {
        return this.logged;
    }

    isAdmin() {
        return this.user && this.user.roles.indexOf('ADMIN') !== -1;
    }

    currentUser() {
        return this.user;
    }
}
