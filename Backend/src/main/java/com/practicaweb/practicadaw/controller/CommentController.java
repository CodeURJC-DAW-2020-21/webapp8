package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/newComment")
    public String createComment(@ModelAttribute Comment comment){
        commentService.save(comment);
        return "redirect:/";
    }
}
