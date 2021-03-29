package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.auxiliar;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
public class EntryController {

    private final UserService userService;
    private final EntryService entryService;

    public EntryController(UserService userService, EntryService entryService) {
        this.userService = userService;
        this.entryService = entryService;
    }

    // New entry
    @PostMapping("/newEntry")
    public String createEntry(@ModelAttribute Entry entry, HttpServletRequest request){
        auxiliar aux = new auxiliar();
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName());
        entry.setUser(user);
        entry.setRegistrationDate(aux.getActualDate());
        entryService.save(entry);
        return "redirect:/";
    }
}
