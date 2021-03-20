package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotPassController {

    private final UserService userService;

    public ForgotPassController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/forgotPassword")
    public String forgotPassword ( @RequestParam ("email") String email, @RequestParam ("password") String password, @RequestParam ("confirmPassword") String confirmPassword){
        User userNewPassword = userService.selectByEmail(email);
        if (AuxUser.verificationPassword(password, confirmPassword)){
            userNewPassword.setPassword(confirmPassword);
            userService.save(userNewPassword);
            return "redirect:/login";
        }
        else{
            return "loginError";
        }
    }
}
