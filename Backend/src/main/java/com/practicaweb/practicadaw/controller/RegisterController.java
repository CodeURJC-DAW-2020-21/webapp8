package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // New user
    @PostMapping("/create_user")
    public String createUser (@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword){
        if (AuxUser.verificationPassword(user.getEncodedPassword(), confirmPassword)){
            user.setRegistrationDate(Auxiliar.getActualDate());
            user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
            user.setRoles("USER");
            user.setImage("defaultImage.jpg");
            userService.save(user);
            return "redirect:/login";
        }
        else {
            return "redirect:/register";
        }
    }
}
