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

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model) {
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

