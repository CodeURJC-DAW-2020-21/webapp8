package com.practicaweb.practicadaw.api.comment;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.api.entry.EntryRestController;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a list of the comments.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of comments is displayed correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of comments not found",
                    content = @Content
            )
    })
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

    @Operation(summary = "Get a comment by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content
            )
    })
    @JsonView(Comments.class)
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findById(id);

        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a list of comments by an id user.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommentRestController.CommentsUser.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Comment not found",
                    content = @Content
            )
    })
    @JsonView(CommentsUser.class)
    @GetMapping("/{id}/comment")
    public ResponseEntity<Comment> getUserComment(@PathVariable long id) {
        Optional<Comment> comment = commentService.findById(id);

        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Create a new Comment")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "500",
                    description = "Comment created correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Comment.Basic.class)
                    )}
            )
    })
    @PostMapping("/")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            Comment comment = new Comment();
            comment.setDescriptionComment(commentDTO.getDescriptionComment());
            Optional<User> userOptional = userService.findByName(principal.getName());
            Optional<Entry> entryOptional = entryService.findById(commentDTO.getIdEntry());
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
