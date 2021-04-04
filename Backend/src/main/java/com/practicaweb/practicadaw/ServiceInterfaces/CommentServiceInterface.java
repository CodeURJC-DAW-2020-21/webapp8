package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.User;

public interface CommentServiceInterface {
    Comment save (Comment comment);
    void deleteCommentByIdUser(long idUser);
    void deleteCommentByIdeEntry(long idEntry);

}
