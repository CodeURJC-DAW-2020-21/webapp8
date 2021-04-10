package com.practicaweb.practicadaw.api.entry;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/entries/")
public class EntryRestController {
    private final UserService userService;
    private final EntryService entryService;
    private final ModelMapper modelMapper;

    public EntryRestController(UserService userService, EntryService entryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.entryService = entryService;
        this.modelMapper = modelMapper;
    }

    private interface EntryComments extends Entry.Basic, Entry.Comments, Comment.Basic{}
    private interface UserEntry extends Entry.Basic, Entry.EntryUser, User.Basic{}

    @JsonView(Entry.Basic.class)
    @GetMapping("/")
    public ResponseEntity<Collection<Entry>> getEntries(){
        Collection<Entry> entries = entryService.selectAll();
        if (!entries.isEmpty()){
            return ResponseEntity.ok(entries);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(Entry.Basic.class)
    @GetMapping("/{idEntry}")
    public ResponseEntity<Entry> getEntry(@PathVariable long idEntry) {
        Optional<Entry> entry = entryService.findById(idEntry);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(UserEntry.class)
    @GetMapping("/{idEntry}/user")
    public ResponseEntity<Entry> getUserByIdEntry(@PathVariable long idEntry){
        Optional<Entry> entry = entryService.findById(idEntry);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(EntryComments.class)
    @GetMapping("/comments")
    public ResponseEntity<Collection<Entry>> getEntryComments(){
        Collection<Entry> entries = entryService.selectAll();
        if (!entries.isEmpty()){
            return ResponseEntity.ok(entries);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(EntryComments.class)
    @GetMapping("/{id}/comments")
    public ResponseEntity<Entry> getCommentsByIdEntry(@PathVariable long id){
        Optional<Entry> entry = entryService.findById(id);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Entry> createEntry(@ModelAttribute EntryDTO entryDTO, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName()).orElseThrow();
        Entry entry = modelMapper.map(entryDTO, Entry.class);
        entry.setUser(user);
        entryService.save(entry);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(entry.getIdEntry()).toUri();
        return ResponseEntity.created(location).body(entry);
    }


}
