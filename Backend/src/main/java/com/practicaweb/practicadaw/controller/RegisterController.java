package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // New user
    @PostMapping("/createUser")
    public String createUser (@ModelAttribute User user){
            user.setNickname("Gelote97");
            user.setRegistrationDate(auxiliar.getActualDate());
            user.setRole("User");
            return "/login";
    }
}
