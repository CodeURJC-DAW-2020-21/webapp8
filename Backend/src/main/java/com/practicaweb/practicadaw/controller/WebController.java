package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
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

@Controller
public class WebController {

    private final int DEFAULT_SIZE_PAGE = 5;
    final EntryService entryService;
    final UserService userService;

    public WebController(EntryService entryService, UserService userService) {
        this.entryService = entryService;
        this.userService = userService;
    }


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));
        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request, Pageable page) {
        Principal principal = request.getUserPrincipal();
        int pageAux = page.getPageNumber();
        page = PageRequest.of(pageAux, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectPageable(page);
        model.addAttribute("entries", entries);
        model.addAttribute("pageToFind", page.getPageNumber() + 1);
        long elements = entries.getNumberOfElements();
        if (elements < DEFAULT_SIZE_PAGE)
            model.addAttribute("showBtn", "display: none");
        else
            model.addAttribute("showBtn", "");
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
