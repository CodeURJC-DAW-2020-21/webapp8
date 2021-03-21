package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Comment;

public interface CommentServiceInterface {
    Comment save (Comment comment);
    void deleteCommentByIdUser(long idUser);
    void deleteCommentByIdeEntry(long idEntry);
}
