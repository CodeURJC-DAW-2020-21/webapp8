import { LoginService } from 'src/app/services/login.service';
import { UsersService } from './../../services/users.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  closeModal: string;

  constructor(private httpClient: HttpClient, public usersService: UsersService, public loginService: LoginService, private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  triggerModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((res) => {
      this.closeModal = `Closed with: ${res}`;
    }, (res) => {
      this.closeModal = `Dismissed ${this.getDismissReason(res)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }

  createUser(event: any,firstname: string, surname: string, email: string, name: string, encodedPassword: string) {
    event.preventDefault();
    this.usersService.createUser(firstname, surname, email, name, encodedPassword).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
}
