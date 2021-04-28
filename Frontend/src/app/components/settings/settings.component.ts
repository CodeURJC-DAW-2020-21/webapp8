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

  constructor(public loginService: LoginService, public usersService: UsersService) { }

  ngOnInit(): void {
  }
}
