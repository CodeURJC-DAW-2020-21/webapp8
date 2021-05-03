import { CommentsService } from 'src/app/services/comment.service';
import { Component, Input, OnInit } from '@angular/core';
import { CommentComponent } from '../comment/comment.component';
import { EntriesService } from 'src/app/services/entries.service';
import { LoginService } from 'src/app/services/login.service';
import { EntryModel } from 'src/app/models/Entry.model';

@Component({
  selector: 'app-new-comment',
  templateUrl: './new-comment.component.html',
  styleUrls: ['./new-comment.component.css']
})
export class NewCommentComponent implements OnInit {

  @Input()
  entry: EntryModel;

  constructor(public commentsService: CommentsService, private entriesService: EntriesService, private loginService: LoginService)  { }

  ngOnInit(): void {
  }

  createComment(event: any, descriptionComment: string) {
    if (this.loginService.isLogged()){
      if (descriptionComment){
        event.preventDefault();
        this.commentsService.postComment(descriptionComment, this.entry.idEntry).subscribe(
          response => {
            let data: any = response;
            this.entriesService.getEntries(0);
          },
          error => console.log(error)
        );
      } else {
        alert('El comentario no puede estar vacio, intentelo de nuevo.');
      }
    } else {
      alert('Para crear un comentario debes iniciar sesion antes.')
    }    
  }
}