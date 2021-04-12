package com.practicaweb.practicadaw.api.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.api.CryptocurrencyRestController;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private String tokenPass;

    public UserRestController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    interface UserEntries extends User.Basic, User.Entries, Entry.Basic{}
    interface UserComments extends User.Basic, User.Comments, Comment.Basic{}
    interface UserEntriesComments extends User.Basic, User.Entries, User.Comments, Entry.Basic, Comment.Basic{}
    interface UserFriends extends User.Basic, User.Friends{}
    interface UserDTOUpdate extends UserDTO.Update{}
    interface UserCryptocurrencies extends User.Cryptocurrencies{}

    //The method getUsers() returns a list of all the registered users.
    @JsonView(User.Basic.class)
    @GetMapping("")
    public ResponseEntity<Collection<User>> getUsers(@RequestParam(required = false) String firstname, @RequestParam(required = false) String surname){
        List<User> users = userService.selectAll();
        if (firstname != null) {
            List<User> usersByFirstName = userService.findByFirstname(firstname);
            return ResponseEntity.ok(usersByFirstName);
        }
        else if (surname != null){
            List<User> usersBySurname = userService.findBySurname(surname);
            return ResponseEntity.ok(usersBySurname);
        }
        else if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(userService.findByName(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @JsonView(User.Basic.class)
    @GetMapping(value="/{id}")
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

    @GetMapping("/{idUser}/favCryptocurrencies")
    public ResponseEntity<Collection<Criptocurrency>> getUserByIdCryptocurrencies(@PathVariable long idUser){
        Optional<User> userOptional = userService.findById(idUser);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Criptocurrency> favCrypto = user.getCriptocurrencies();
            return ResponseEntity.ok(favCrypto);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @JsonView(UserFriends.class)
    @GetMapping("/{id}/friends")
    public ResponseEntity<User> getUserFriends(@PathVariable long id){
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/friends/{idFriend}")
    public ResponseEntity<User> addUserFriends(@PathVariable long idFriend, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        Optional<User> userFriendOptional = userService.findById(idFriend);
        if (userOptional.isPresent() && userFriendOptional.isPresent()){
            User user = userOptional.get();
            User userFriend = userFriendOptional.get();
            List<User> friendsList = new ArrayList<>();
            friendsList.add(userFriend);
            user.setFriends(friendsList);
            userService.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/friends/{idFriend}")
    public ResponseEntity<User> deleteFriend(@PathVariable long idFriend, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<User> userFriends = user.getFriends();
            for (int i = 0; i < userFriends.size(); i++) {
                if (userFriends.get(i).getIdUser() == idFriend) {
                    userFriends.remove(i);
                }
            }
            user.setFriends(userFriends);
            userService.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
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

    @PostMapping("/password")
    public ResponseEntity<User> forgotPasswordREST(@RequestBody String email){
        String sender = "forocoin.soporteoficial@gmail.com";
        String emailPass = "forocoin1";
        String destinatary = email;
        String response = userService.forgotPassword(email);
        if (!response.startsWith("Invalid email id.")){
            tokenPass = response;
            response = "https://localhost:8443/password?tokenPass=" + response;
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.user", sender);
            properties.put("mail.smtp.clave", emailPass);
            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            try{
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatary));
                message.setSubject("Reestablecer contraseña de foroCoin");
                message.setText("Pinche aqui para reestablecer su contraseña:" + response);
                Transport transport = session.getTransport("smtp");
                transport.connect("smtp.gmail.com", sender, emailPass);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (Exception e){

            }
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/reset_password")
//    public ResponseEntity<User> resetPassword(@RequestBody String password, @RequestBody String passwordConfirmation){
//        if ((password != null && passwordConfirmation != null) && (password.equals(passwordConfirmation))) {
//            userService.resetPassword(tokenPass, password);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

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
