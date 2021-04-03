package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class PostController {

    private final EntryService entryService;
    private final UserService userService;
    @Autowired
    CriptocurrencyService criptocurrencyService;

    public PostController(EntryService entryService, UserService userService, CriptocurrencyService criptocurrencyService) {
        this.entryService = entryService;
        this.userService = userService;
        this.criptocurrencyService = criptocurrencyService;
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }



    @GetMapping("/password")
    public String passwordReset(Model model){
        return "password";
    }

    @GetMapping("/passwordEmail")
    public String passwordEmail(Model model){
        return "passwordEmail";
    }

    @GetMapping("/criptomonedas")
    public String criptomonedas(Model model, HttpServletRequest request) {
        List<Criptocurrency> criptocurrencies = criptocurrencyService.selectAll();
        model.addAttribute("criptocurrencies", criptocurrencies);
        return "criptomonedas";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if(principal != null) {
            User user = userService.findByName(principal.getName());
            List<Criptocurrency> criptocurrenciesFav = user.getCriptocurrencies();
            model.addAttribute("criptocurrenciesFav", criptocurrenciesFav);
        }
        return "cript_favoritas";
    }

    @GetMapping("/users")
    public String users(Model model, HttpServletRequest request){
        List<User> users = userService.selectAll();
        model.addAttribute("users", users);
        return "userList";
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

