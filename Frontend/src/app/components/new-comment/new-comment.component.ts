import { CommentsService } from 'src/app/services/comment.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-new-comment',
  templateUrl: './new-comment.component.html',
  styleUrls: ['./new-comment.component.css']
})
export class NewCommentComponent implements OnInit {

  @Input()
  idEntry: number;

  constructor(public commentsService: CommentsService) { }

  ngOnInit(): void {
  }

  createComment(event: any, descriptionComment: string) {
    event.preventDefault();
    this.commentsService.postComment(descriptionComment, this.idEntry).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
}
