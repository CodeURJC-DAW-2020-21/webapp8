package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {


    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        HttpSession misession = (HttpSession) request.getSession();
        User  userActual = (User)misession.getAttribute("user");
        if (userActual == null){
            model.addAttribute("logged", false);
            return "index";
        }
        model.addAttribute("userName", userActual.getNickname());
        model.addAttribute("logged", true);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        return "adminPage";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @GetMapping("/password")
    public String passwordReset(Model model){
        return "password";
    }

    @GetMapping("/criptomonedas")
    public String criptomonedas(Model model) {
        model.addAttribute("userName", "Marcos");
        model.addAttribute("logged", true);
        return "criptomonedas";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model) {
        model.addAttribute("userName", "Marcos");
        model.addAttribute("logged", true);
        return "cript_favoritas";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpServletRequest request) {
        HttpSession misession = (HttpSession) request.getSession();
        User  userActual = (User)misession.getAttribute("user");
        model.addAttribute("name", userActual.getName());
        model.addAttribute("surname", userActual.getSurname());
        model.addAttribute("userName", userActual.getNickname());
        model.addAttribute("email", userActual.getEmail());
        model.addAttribute("logged", true);
        return "settings";
    }

    @GetMapping("/Bitcoin")
    public String bitcoin(Model model) {
        return "Bitcoin";
    }

    @GetMapping("/Dogecoin")
    public String doge(Model model) {
        return "Dogecoin";
    }

    @GetMapping("/Badger")
    public String badger(Model model) {
        return "Badger";
    }
}

