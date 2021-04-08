package com.practicaweb.practicadaw.api.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    interface UserEntries extends User.Basic, User.Entries, Entry.Basic{}
    interface UserComments extends User.Basic, User.Comments, Comment.Basic{}
    interface UserEntriesComments extends User.Basic, User.Entries, User.Comments, Entry.Basic, Comment.Basic{}
    interface UserDTOUpdate extends UserDTO.Update{}

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

    @PostMapping("/")
    public ResponseEntity<User> createUser(@ModelAttribute UserDTO userDTO) throws IOException, SQLException {
        User user = modelMapper.map(userDTO, User.class);
        userService.createUser(user);
        if(userDTO.getImage() != null){
            userService.updateUserImage(user, userDTO.getImage());
        }
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdUser()).toUri();

        return ResponseEntity.created(location).body(user);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImage(@PathVariable long id) throws SQLException {

        Optional<User> user = userService.findById(id);

        if (user.isPresent()){
            int profileImageLength = (int) user.get().getImage().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(new ByteArrayResource(user.get().getImage().getBytes(1, profileImageLength)));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            userService.delete(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(UserDTOUpdate.class)
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @ModelAttribute UserDTO userDTO) throws SQLException, IOException {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.updateUser(user, userDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
