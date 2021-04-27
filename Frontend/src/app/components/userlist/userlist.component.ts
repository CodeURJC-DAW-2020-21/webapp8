import { UserModel } from 'src/app/models/User.model';
import { UsersService } from './../../services/users.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  users: UserModel[];
  value: any;

  constructor(public usersService: UsersService, private router: Router) { }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.users = [];
    this.usersService.getUsers().subscribe(
      users => this.users = users
    );
  }

  deleteUserById(idUser: number) {
    this.usersService.deleteUserById(idUser).subscribe(
      _ => this.router.navigate(['/userslist2']),
      error => console.error(error)
    );
  }

}
