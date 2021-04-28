import { Component, Input, OnInit } from '@angular/core';
import { CommentModel } from 'src/app/models/Comment.model';
import { EntriesService } from 'src/app/services/entries.service';
import { UsersService } from 'src/app/services/users.service';
import { CommentsService } from 'src/app/services/comment.service';
import { UserModel } from 'src/app/models/User.model';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input()
  idEntry: number;
  comments: CommentModel[];
  commentWuser: CommentModel = new CommentModel;
  users: UserModel[];
  constructor(private commentsService: CommentsService, private entriesService: EntriesService, private usersService: UsersService) { }

  ngOnInit(): void {
    this.getCommentsByIdEntry(this.idEntry);
  }

  getCommentsByIdEntry(idEntry: number): void {
    this.comments = [];
    this.entriesService.getCommentsByIdEntry(idEntry).subscribe(
      response => {
        let data: any = response;
        for (var i = 0; i < data.comment.length; i++) {
          let auxComment = data.comment[i];
          this.comments.push(auxComment);
        }
      }
    );
  }

  isNotEmpty(componentEmpty: any[]): boolean{
    if (componentEmpty.length === 0){
      return false;
    }
    else if (componentEmpty.length > 0){
      return true;
    }
    return false;
  }

  getUserByIdComment(id: number): CommentModel{
    this.commentsService.getUserByIdComment(id).subscribe(
      response => {
        let data: any = response;
        this.commentWuser.user = data.user;
        return this.commentWuser;
      }
    );
    return this.commentWuser;
  }


}
