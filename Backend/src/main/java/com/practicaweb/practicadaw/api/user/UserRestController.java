package com.practicaweb.practicadaw.api.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {

    @Autowired
    UserService userService;

    interface UserEntries extends User.Basic, User.Entries, Entry.Basic{}
    interface UserComments extends User.Basic, User.Comments, Comment.Basic{}
    interface UserEntriesComments extends User.Basic, User.Entries, User.Comments, Entry.Basic, Comment.Basic{}

    //The method getUsers() returns a list of all the registered users.
    @JsonView(User.Basic.class)
    @GetMapping("/")
    public ResponseEntity<Collection<User>> getUsers(){
        Collection<User> users = userService.selectAll();

        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(User.Basic.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(UserEntries.class)
    @GetMapping("/entries")
    public ResponseEntity<Collection<User>> getUserEntries(){
        Collection<User> users = userService.selectAll();

        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(UserEntries.class)
    @GetMapping("/{id}/entries")
    public ResponseEntity<User> getUserByIdEntries(@PathVariable long id){
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(UserComments.class)
    @GetMapping("/comments")
    public ResponseEntity<Collection<User>> getUserComments(){

        Collection<User> users = userService.selectAll();

        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(UserComments.class)
    @GetMapping("/{id}/comments")
    public ResponseEntity<User> getUserByIdComments(@PathVariable long id){
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @JsonView(UserEntriesComments.class)
    @GetMapping("/entries/comments")
    public ResponseEntity<Collection<User>> getUserEntriesAndComments(){
        Collection<User> users = userService.selectAll();

        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(UserEntriesComments.class)
    @GetMapping("/{id}/entries/comments")
    public ResponseEntity<User> getUserByIdEntriesAndComments(@PathVariable long id){
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
