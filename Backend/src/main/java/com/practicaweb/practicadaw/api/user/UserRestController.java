package com.practicaweb.practicadaw.api.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.api.CryptocurrencyRestController;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
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
    interface UserCryptocurrencies extends User.Cryptocurrencies, User.Basic{}

    //The method getUsers() returns a list of all the registered users.
    @Operation(summary = "Get a list of the web app users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of users is displayed correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of users not found",
                    content = @Content
            )
    })
    @JsonView(User.Basic.class)
    @GetMapping("")
    public ResponseEntity<Collection<User>> getUsers(@Parameter(description = "Use this parameter to filter users by their name.") @RequestParam(required = false) String firstname,@Parameter(description = "Use this parameter to filter users by their surname.") @RequestParam(required = false) String surname){
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

    @Operation(summary = "Get information about the logged user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User information displayed correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not logged, please log in.",
                    content = @Content
            )
    })
    @JsonView(User.Basic.class)
    @GetMapping("/me")
    public ResponseEntity<User> me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return ResponseEntity.ok(userService.findByName(principal.getName()).orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get a user by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
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
    @JsonView(User.Basic.class)
    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUser(@Parameter(description = "This is the id of the user you're looking for.") @PathVariable long id) {
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

    @GetMapping("/cryptocurrencies")
    public ResponseEntity<Collection<Criptocurrency>> getUserCryptocurrencies(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Criptocurrency> favCrypto = user.getCriptocurrencies();
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idUser}/cryptocurrencies")
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

    @Operation(summary = "Get a list of the logged user friends.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of friends is displayed correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of friends not found",
                    content = @Content
            )
    })
    @GetMapping("/friends")
    public ResponseEntity<User> getUserFriends(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get a list of user friends by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friends list found",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of user friends not found",
                    content = @Content
            )
    })
    @JsonView(UserFriends.class)
    @GetMapping("/{id}/friends")
    public ResponseEntity<User> getUserByIdFriends(@Parameter(description = "Id of the user you're looking for.") @PathVariable long id){
        Optional<User> user = userService.findById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a friend by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friend added correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Friend id not found",
                    content = @Content
            )
    })
    @JsonView(UserFriends.class)
    @PostMapping("/friends/{idFriend}")
    public ResponseEntity<User> addUserFriends(@Parameter(description = "Id of the friend you want to add") @PathVariable long idFriend, HttpServletRequest request){
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

    @Operation(summary = "Delete a friend by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Friend deleted correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Friend id not found",
                    content = @Content
            )
    })
    @DeleteMapping("/friends/{idFriend}")
    public ResponseEntity<User> deleteFriend(@Parameter(description = "Id of the friend you want to delete") @PathVariable long idFriend, HttpServletRequest request){
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

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "500",
                    description = "User created correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            )
    })
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws IOException, SQLException {
        User user = modelMapper.map(userDTO, User.class);
        userService.createUser(user);
        if(userDTO.getImage() != null){
            userService.updateUserImage(user, userDTO.getImage());
        }
        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdUser()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "View your profile image")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image shown correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Image not found",
                    content = @Content
            )
    })
    @GetMapping("/image")
    public ResponseEntity<Object> getImage(HttpServletRequest request) throws SQLException {
        Principal principal = request.getUserPrincipal();
        Optional<User> user = userService.findByName(principal.getName());

        if (user.isPresent()){
            int profileImageLength = (int) user.get().getImage().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(new ByteArrayResource(user.get().getImage().getBytes(1, profileImageLength)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "View a user image by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Image shown correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User id not found",
                    content = @Content
            )
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getImageByUserID(@Parameter(description = "Insert the id of the user you're looking for") @PathVariable long id) throws SQLException {

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            int profileImageLength = (int) user.get().getImage().length();
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(new ByteArrayResource(user.get().getImage().getBytes(1, profileImageLength)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Send an email to recover your password")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Email sent correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid email supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content
            )
    })
    @JsonView(UserDTO.ForgottenPassword.class)
    @PostMapping("/password")
    public ResponseEntity<User> forgotPasswordREST(@RequestBody UserDTO userDTO){
        String sender = "forocoin.soporteoficial@gmail.com";
        String emailPass = "forocoin1";
        String email = userDTO.getEmail();
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
    @JsonView(UserCryptocurrencies.class)
    @GetMapping("/recommended")
    public ResponseEntity<Collection<User>> recommendedCryptos(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            List<Criptocurrency> usercriptocurrencies = user.getCriptocurrencies();
            int i = 0; //counter size user's friends list
            List<User> friendListFinal = new ArrayList<User>();
            List<User> friendList = user.getFriends();
            while (i<friendList.size()){
                User friend = friendList.get(i);
                List<Criptocurrency> listFriendCripto = friend.getCriptocurrencies();
                int j = 0; //counter size friend's cryptocurrency list
                int cont = 0;
                while (j<usercriptocurrencies.size()){
                    for(int k = 0; k < listFriendCripto.size(); k++) {
                        if (usercriptocurrencies.get(j).equals(listFriendCripto.get(k))) {
                            listFriendCripto.remove(listFriendCripto.get(k));
                            cont++;
                        }
                    }
                    j++;
                }
                i ++;
                if (cont>=2 && !listFriendCripto.isEmpty()){
                    friendListFinal.add(friend);
                }
            }
            return ResponseEntity.ok(friendListFinal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Delete user by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
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
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@Parameter(description = "Id of the user you want to delete") @PathVariable long id){
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            userService.delete(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update your user account")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated correctly",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.Basic.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid user parameters",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content
            )
    })
    @JsonView(UserDTOUpdate.class)
    @PatchMapping("/")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody UserDTO userDTO) throws SQLException, IOException {
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.updateUser(user, userDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/{id}/image")
    public ResponseEntity<Object> updateImage(@PathVariable long id,@RequestParam MultipartFile image) throws SQLException, IOException {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            userService.updateUserImage(user, image);
            userService.save(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
