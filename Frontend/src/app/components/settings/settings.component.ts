import { UsersService } from './../../services/users.service';
import { Component, OnInit } from '@angular/core';
import { from } from 'rxjs';
import { UserModel } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: UserModel;

  constructor(public loginService: LoginService, public usersService: UsersService) { }

  ngOnInit(): void {
  }

  updateUser(event: any, firstname: string, surname: string) {
    event.preventDefault();
    this.usersService.patchUser(firstname, surname).subscribe(
      user => this.user = user,
      error => console.log(error)
    );

  }

  updateImage(event: any, idUser: number, type: string, image: File) {
    event.preventDefault();
    this.usersService.postImage(idUser, type, image).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }

}
