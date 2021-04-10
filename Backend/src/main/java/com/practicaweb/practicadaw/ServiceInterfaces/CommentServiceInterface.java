package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.User;

import java.util.List;
import java.util.Optional;

public interface CommentServiceInterface {
    Comment save (Comment comment);
    void deleteCommentByIdUser(long idUser);
    void deleteCommentByIdeEntry(long idEntry);
    List<Comment> selectAll();
    Optional<Comment> findById(long idComment);

}
