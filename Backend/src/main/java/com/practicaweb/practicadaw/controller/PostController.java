package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    private final EntryService entryService;

    public PostController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        HttpSession mysession = request.getSession();
        User  actualUser = (User)mysession.getAttribute("actualUser");
        List<Entry> entries = entryService.selectAll();
        model.addAttribute("entries", entryService.selectAll());
        if (actualUser == null){
            model.addAttribute("logged", false);
            return "index";
        }
        model.addAttribute("userName", actualUser.getNickname());
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
    public String criptomonedas(Model model, HttpServletRequest request) {
        HttpSession mysession = request.getSession();
        User  actualUser = (User)mysession.getAttribute("actualUser");
        if (actualUser == null){
            model.addAttribute("logged", false);
            return "criptomonedas";
        }
        model.addAttribute("userName", actualUser.getNickname());
        model.addAttribute("logged", true);
        return "criptomonedas";
    }

    @GetMapping("/favorite_cryptocurrencies")
    public String favorites(Model model, HttpServletRequest request) {
        HttpSession mysession = request.getSession();
        User  actualUser = (User)mysession.getAttribute("actualUser");
        if (actualUser == null){
            model.addAttribute("logged", false);
            return "cript_favoritas";
        }
        model.addAttribute("userName", actualUser.getNickname());
        model.addAttribute("logged", true);
        return "cript_favoritas";
    }

    @GetMapping("/settings")
    public String settings(Model model, HttpServletRequest request) {
        HttpSession mysession = request.getSession();
        User  actualUser = (User)mysession.getAttribute("actualUser");
        model.addAttribute("name", actualUser.getName());
        model.addAttribute("surname", actualUser.getSurname());
        model.addAttribute("userName", actualUser.getNickname());
        model.addAttribute("email", actualUser.getEmail());
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

