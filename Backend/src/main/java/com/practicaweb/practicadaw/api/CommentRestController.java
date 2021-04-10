package com.practicaweb.practicadaw.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;
    @Autowired
    UserService userService;
    @Autowired
    EntryService entryService;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    interface Comments extends Comment.Basic{}
    interface CommentsUser extends Comment.Basic, Comment.CommentUser, User.Basic{}

    @JsonView(Comments.class)
    @GetMapping("/")
    public ResponseEntity<Collection<Comment>> getComments(){

        List<Comment> comments = commentService.selectAll();

        if (!comments.isEmpty()){
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @JsonView(Comments.class)
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findById(id);

        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(CommentsUser.class)
    @GetMapping("/{id}/comment")
    public ResponseEntity<Comment> getUserComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findById(id);

        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{idEntry}")
    public ResponseEntity<Comment> createComment(@ModelAttribute Comment comment, HttpServletRequest request, @PathVariable long idEntry) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Optional<User> userOptional = userService.findByName(principal.getName());
            Optional<Entry> entryOptional = entryService.findById(idEntry);
            User user = userOptional.get();
            Entry entry = entryOptional.get();
            commentService.createComment(comment, user, entry);
            URI location = fromCurrentRequest().path("/{id}").buildAndExpand(comment.getIdComment()).toUri();
            return ResponseEntity.created(location).body(comment);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
