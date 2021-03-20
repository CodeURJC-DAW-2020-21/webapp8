package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createEntry(@ModelAttribute Entry entry){
//        userService.selectById(id);
        entryService.save(entry);
        return "redirect:/";
    }
}
