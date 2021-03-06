import { Router } from '@angular/router';
import { UsersService } from './../../services/users.service';
import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/User.model';
import { LoginService } from 'src/app/services/login.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  user: UserModel;
  form: FormGroup;

  constructor(public loginService: LoginService, public usersService: UsersService, public fb: FormBuilder) {
    this.form = this.fb.group({image: [null]});
  }

  ngOnInit(): void {
    this.getUser();
  }

  reLoad(){
    window.location.reload();
  }

  getUser() {
    this.usersService.getUserById(this.loginService.currentUser().idUser).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
  }

  updateUser(event: any, firstname: string, surname: string) {
    const okResponse = window.confirm('¿Estas seguro de que deseas guardar los cambios?');
    if (okResponse){
      event.preventDefault();
      this.usersService.patchUser(firstname, surname).subscribe(
      user => this.user = user,
      error => console.log(error)
    );
      window.location.reload();
    } else {

    }
  }

  upload(event) {
    const file = (event.target as HTMLInputElement).files[0];
    this.form.patchValue({
      image: file
    });
    this.form.get('image').updateValueAndValidity();
 }

 submit() {
  const formData: any = new FormData();
  formData.append('image', this.form.get('image').value);

  this.usersService.postImage(this.loginService.currentUser().idUser, formData).subscribe(
    response => console.log(response),
    error => console.log(error)
  );
  }
}
