package com.practicaweb.practicadaw.api.entry;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/entries")
public class EntryRestController {
    private final UserService userService;
    private final EntryService entryService;
    private final ModelMapper modelMapper;
    private final int DEFAULT_SIZE_PAGE = 5;
    Pageable page = null;

    public EntryRestController(UserService userService, EntryService entryService, ModelMapper modelMapper) {
        this.userService = userService;
        this.entryService = entryService;
        this.modelMapper = modelMapper;
    }

    private interface EntryComments extends Entry.Basic, Entry.Comments, Comment.Basic{}
    private interface UserEntry extends Entry.Basic, Entry.EntryUser, User.Basic{}

    @Operation(summary = "Get a list of entries using a number of page.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Entry.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid number of page supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry not found",
                    content = @Content
            )
    })
    @JsonView(Entry.Basic.class)
    @GetMapping("/")
    public ResponseEntity<Collection<Entry>> getEntries(@Parameter(description = "number of the page you want to get") @RequestParam(defaultValue = "0") int numOfPage){
        page = PageRequest.of(numOfPage, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectPageable(page);
        if (!entries.isEmpty()){
            return ResponseEntity.ok(entries.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get a list of entries using a number of page.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserEntry.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid number of page supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry not found",
                    content = @Content
            )
    })
    @JsonView(UserEntry.class)
    @GetMapping("/entry/users/")
    public ResponseEntity<Collection<Entry>> getEntriesWithUser(@Parameter(description = "number of the page you want to get") @RequestParam(defaultValue = "0") int numOfPage){
        page = PageRequest.of(numOfPage, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectPageable(page);
        if (!entries.isEmpty()){
            return ResponseEntity.ok(entries.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get an entry by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Entry.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry not found",
                    content = @Content
            )
    })
    @JsonView(Entry.Basic.class)
    @GetMapping("/{idEntry}")
    public ResponseEntity<Entry> getEntry(@PathVariable long idEntry) {
        Optional<Entry> entry = entryService.findById(idEntry);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a user by the id of an entry.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserEntry.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @JsonView(UserEntry.class)
    @GetMapping("/{idEntry}/user")
    public ResponseEntity<Entry> getUserByIdEntry(@PathVariable long idEntry){
        Optional<Entry> entry = entryService.findById(idEntry);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a list of entries and its comments.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EntryComments.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid number of page supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry not found",
                    content = @Content
            )
    })
    @JsonView(EntryComments.class)
    @GetMapping("/comments")
    public ResponseEntity<Collection<Entry>> getEntryComments(@RequestParam(defaultValue = "0") int numOfPage){
        page = PageRequest.of(numOfPage, DEFAULT_SIZE_PAGE, Sort.by("registrationDate").descending());
        Page<Entry> entries = entryService.selectPageable(page);
        if (!entries.isEmpty()){
            return ResponseEntity.ok(entries.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get a list of comments by an id entry.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entry found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EntryComments.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entry not found",
                    content = @Content
            )
    })
    @JsonView(EntryComments.class)
    @GetMapping("/{id}/comments")
    public ResponseEntity<Entry> getCommentsByIdEntry(@PathVariable long id){
        Optional<Entry> entry = entryService.findById(id);
        return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new entry")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "500",
                    description = "Entry created correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            )
    })
    @PostMapping("/")
    public ResponseEntity<Entry> createEntry(@RequestBody EntryDTO entryDTO, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName()).orElseThrow();
        Entry entry = modelMapper.map(entryDTO, Entry.class);
        entry.setUser(user);
        entry.setRegistrationDate(LocalDateTime.now());
        entryService.save(entry);
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(entry.getIdEntry()).toUri();
        return ResponseEntity.created(location).build();
    }


}
