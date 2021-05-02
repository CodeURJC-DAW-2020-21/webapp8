import { CommentsService } from 'src/app/services/comment.service';
import { Component, Input, OnInit } from '@angular/core';
import { CommentComponent } from '../comment/comment.component';
import { EntriesService } from 'src/app/services/entries.service';

@Component({
  selector: 'app-new-comment',
  templateUrl: './new-comment.component.html',
  styleUrls: ['./new-comment.component.css']
})
export class NewCommentComponent implements OnInit {

  @Input()
  idEntry: number;
  response: any;

  constructor(public commentsService: CommentsService, private entriesService: EntriesService)  { }

  ngOnInit(): void {
    // this.response.headers.lazyInit.Scopes[0].headers;
  }

  createComment(event: any, descriptionComment: string) {
    event.preventDefault();
    this.commentsService.postComment(descriptionComment, this.idEntry).subscribe(
      response => {
        let data: any = response;
        this.entriesService.getEntries(0);
      },
      error => console.log(error)
    );
  }
}
