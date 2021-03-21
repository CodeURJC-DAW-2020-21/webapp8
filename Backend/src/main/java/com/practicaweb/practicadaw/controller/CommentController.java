package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final EntryService entryService;

    public CommentController(CommentService commentService, UserService userService, EntryService entryService) {
        this.userService = userService;
        this.commentService = commentService;
        this.entryService = entryService;
    }

    @PostMapping("/newComment")
    public String createComment(@ModelAttribute Comment comment, HttpServletRequest request){
        HttpSession mysession = request.getSession();
        User actualUser = (User)mysession.getAttribute("actualUser");
        comment.setUser(actualUser);
        commentService.save(comment);
        return "redirect:/";
    }
}
