import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentModel } from '../models/Comment.model';

const BASE_URL = "/api/comments/";

@Injectable({ providedIn: 'root' })
export class CommentsService {
    constructor(private httpClient: HttpClient) { }

    getUserByIdComment(idComment: number): Observable<CommentModel>{
        let url = idComment + "/commentUser";
        return this.httpClient.get(BASE_URL + url).pipe() as Observable<CommentModel>;
      }

    postComment(descriptionComment: string, idEntry: number): Observable<any> {
      let aux = this.httpClient.post(BASE_URL, {descriptionComment, idEntry}, {observe: 'response'}).pipe() as Observable<any>;
      return aux;
    }
}
