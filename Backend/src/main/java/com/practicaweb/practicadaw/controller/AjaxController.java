package com.practicaweb.practicadaw.controller;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

    private final EntryService entryService;
    private final int DEFAULT_SIZE_PAGE = 10;

    public AjaxController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/getPagedEntries")
    public String getPagedEntries(Model model) {
        Pageable page = PageRequest.of(0, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectAll(page);
        model.addAttribute("entries", entries);
        page = PageRequest.of(1, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> moreEntries = entryService.selectAll(page);
        model.addAttribute("moreEntries", moreEntries);
        return "index";
    }
}