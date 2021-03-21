package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UserController {

    private final UserService userService;
    private final EntryService entryService;
    private final CommentService commentService;

    public UserController(UserService userService, EntryService entryService, CommentService commentService) {
        this.userService = userService;
        this.entryService = entryService;
        this.commentService = commentService;
    }

    @PostMapping("/delete_user")
    public String deleteUser(Model model, @RequestParam long idUser){
        commentService.deleteCommentByIdUser(idUser);
        Entry entryToDelete = entryService.selectById(idUser);
        commentService.deleteCommentByIdeEntry(entryToDelete.getIdEntry());
        entryService.deleteEntryByIdUser(idUser);
        userService.deleteUserById(idUser);
        return "redirect:/users";
    }

}
