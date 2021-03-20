package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkLogin")
    public String loginUser (@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request){
        User actualUser = userService.selectByEmail(email);
        if (actualUser == null)
            return "loginError";
        if (AuxUser.verificationEmail(actualUser.getEmail(), email) && AuxUser.verificationPassword(actualUser.getPassword(), password)){
            HttpSession mysession= request.getSession(true);
            mysession.setAttribute("actualUser",actualUser);
            return "redirect:/";
        } else {
            return "loginError";
        }
    }
}
