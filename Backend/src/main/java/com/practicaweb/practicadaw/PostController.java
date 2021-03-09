package com.practicaweb.practicadaw;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {


    @GetMapping("/criptomonedas")
    public String criptomonedas(Model model) {
        return "criptomonedas";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "404";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model) {
        return "cript_favoritas";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        return "settings";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/recover_password")
    public String recoverPassword(Model model) {
        return "password";
    }
}

