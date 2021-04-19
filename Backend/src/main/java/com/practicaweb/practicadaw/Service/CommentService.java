package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.CommentServiceInterface;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.CommentRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Comment> selectAll(){
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(long idComment) {
        return commentRepository.findById(idComment);
    }

    public void createComment(Comment comment, User user, Entry entry){

        comment.setRegistrationDate(LocalDateTime.now());
        comment.setUser(user);
        comment.setEntry(entry);
        comment.setDescriptionComment(comment.getDescriptionComment());
        commentRepository.save(comment);
    }

}
