package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class ForgotPassController {
    @PostMapping("/forgotPassword")
    public String forgotPassword (@ModelAttribute User user){
        if (AuxUser.verificationPassword(user.getPassword(), "")){
            user.setPassword(user.getPassword());
            return "redirect:/index";
        }
        else {
            return "redirect:/password";
        }
    }
}
