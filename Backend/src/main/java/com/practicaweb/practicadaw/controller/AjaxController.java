package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.model.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AjaxController {

    private final EntryService entryService;
    private final int DEFAULT_SIZE_PAGE = 10;

    public AjaxController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/getPagedEntries")
    public ResponseEntity<Page<Entry>> getPagedEntries(Model model, @RequestParam(value = "pageToFind") int pageToFind) {
        Pageable page = PageRequest.of(pageToFind, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectPageable(page);
        model.addAttribute("entries", entries);
        if (!entries.isEmpty()){
            return ResponseEntity.accepted().body(entries);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}