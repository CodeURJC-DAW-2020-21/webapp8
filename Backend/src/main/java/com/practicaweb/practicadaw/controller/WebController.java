package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WebController {

    private final int DEFAULT_SIZE_PAGE = 10;
    @Autowired
    EntryService entryService;

    @Autowired
    UserService userService;


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        int pageToFind = 0;
        Pageable page = PageRequest.of(pageToFind, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectAll(page);
        long elements = entries.getTotalElements();
        model.addAttribute("entries", entries);
        if (elements <= 10)
            model.addAttribute("showBtn", "display: none");
        else
            model.addAttribute("showBtn", "display: block");
        page = PageRequest.of(1, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        entries = entryService.selectAll(page);
        model.addAttribute("moreEntries", entries);
        model.addAttribute("pageToFind", pageToFind + 1);

        if (principal != null) {

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByName(principal.getName()).orElseThrow();
            for(int i = 0; i< user.getFriends().size();i++){
                user.getFriends().get(i).setFollow("images/DejarDeSeguir.png");
            }
            user.setFollow("images/blank.png");
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginError")
    public String loginerror() {
        return "loginError";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
