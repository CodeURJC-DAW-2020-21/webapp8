package com.practicaweb.practicadaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    @GetMapping("/clearSession")
    public String clearSession(Model model, HttpServletRequest request){
        HttpSession mysession = request.getSession();
        mysession.setAttribute("actualUser", null);
        return "redirect:/";
    }
}
