import { UserModel } from 'src/app/models/User.model';
import { UsersService } from './../../services/users.service';
import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  users: UserModel[];
  value: any;

  constructor(public usersService: UsersService, private router: Router, activatedRoute: ActivatedRoute) {
    this.getUsers();
  }

  ngOnInit(): void {
    this.getUsers();
  }

  refresh(idUser: number){
    if(idUser !== 1){
      this.deleteUserById(idUser);
      let newUsers: UserModel[] = this.users;
      this.users.forEach(function(user){
        if (user.idUser === idUser){
          let index = newUsers.indexOf(user);
          newUsers.splice(index, 1);
        }
      });
    } else {
      alert("No puedes eliminar al admin.")
    }
  }

  getUsers(){
    this.users = [];
    this.usersService.getUsers().subscribe(
      users => this.users = users
    );
  }

  deleteUserById(idUser: number) {
    this.usersService.deleteUserById(idUser).subscribe(
      response => console.log(response),
      error => console.error(error)
    );
  }
}
