package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class UserController {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");
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
        userService.deleteUserById(idUser);
        return "redirect:/users";
    }


}
