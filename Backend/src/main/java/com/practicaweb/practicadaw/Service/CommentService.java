package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.CommentServiceInterface;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.repository.CommentRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService implements CommentServiceInterface {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;


    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentByIdUser(long idUser) {
        commentRepository.deleteCommentByIdUser(idUser);
    }

    @Override
    @Transactional
    public void deleteCommentByIdeEntry(long idEntry) {
        commentRepository.deleteCommentByIdEntry(idEntry);
    }

}
