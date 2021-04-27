import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-main-forum',
  templateUrl: './main-forum.component.html',
  styleUrls: ['./main-forum.component.css']
})
export class MainForumComponent implements OnInit {

  closeModal: string;
  
  constructor(private modalService: NgbModal) {}

  ngOnInit(): void {
  }
}
