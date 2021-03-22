package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
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
    public String createUser (@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword){
        if (AuxUser.verificationPassword(user.getPassword(), confirmPassword)){
            user.setRegistrationDate(auxiliar.getActualDate());
            user.setRole("admin");
            user.setImage("defaultImage");
            userService.save(user);
            return "redirect:/login";
        }
        else {
            return "redirect:/register";
        }
    }
}