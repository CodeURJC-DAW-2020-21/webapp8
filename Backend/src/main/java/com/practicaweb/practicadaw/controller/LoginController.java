package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // New user
    @PostMapping("/loginUser")
    public String loginUser (@ModelAttribute User user){
        if (user.emailExists(user.getEmail()) && user.passwordExists(user.getPassword())){
            return "/index";
        }
        else{
            return "/login";
        }
    }
}
