package com.practicaweb.practicadaw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Marcos");
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

    @GetMapping("/forgot_password")
    public String passwordReset(Model model){
        return "password";
    }

    @GetMapping("/criptomonedas")
    public String criptomonedas(Model model) {
        model.addAttribute("name", "Marcos");
        model.addAttribute("logged", false);
        return "criptomonedas";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model) {
        model.addAttribute("name", "Marcos");
        model.addAttribute("logged", false);
        return "cript_favoritas";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
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

