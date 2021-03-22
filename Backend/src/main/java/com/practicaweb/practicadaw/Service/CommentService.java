package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.CommentServiceInterface;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.repository.CommentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService implements CommentServiceInterface {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
