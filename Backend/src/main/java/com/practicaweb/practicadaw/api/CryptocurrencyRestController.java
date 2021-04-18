package com.practicaweb.practicadaw.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.api.user.UserRestController;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cryptocurrencies/")
public class CryptocurrencyRestController {

    @Autowired
    CriptocurrencyService criptocurrencyService;

    @Autowired
    UserService userService;

    @Operation(summary = "Get a list of the web app cryptocurrencies.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of cryptocurrencies is displayed correctly",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of cryptocurrencies not found",
                    content = @Content
            )
    })
    @GetMapping("/")
    public Collection<Criptocurrency> getCryptocurrencies(){
        return criptocurrencyService.selectAll();
    }


    @Operation(summary = "Get a cryptocurrency by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "cryptocurrency found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "cryptocurrency not found",
                    content = @Content
            )
    })
    @GetMapping("{id}")
    public ResponseEntity<Criptocurrency> getCriptocurrency(@PathVariable long id){
        Optional<Criptocurrency> cripto = criptocurrencyService.findById(id);

        return cripto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Add a cryptocurrency by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "cryptocurrency added correctly",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "cryptocurrency id not found",
                    content = @Content
            )
    })
    @PostMapping("/{id}/cryptocurrencies")
    public ResponseEntity<Criptocurrency> addFavCrypto(@PathVariable long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        Optional<Criptocurrency> cryptoOptional = criptocurrencyService.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            Criptocurrency crypto = cryptoOptional.get();
            if(!user.getCriptocurrencies().contains(crypto)){
                user.addCript(crypto);
                crypto.setImage("images/star.svg");
            }
            userService.save(user);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Delete a favCryptocurrency by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "favCryptocurrency deleted correctly",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "favCryptocurrency id not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}/cryptocurrencies")
    public ResponseEntity<Criptocurrency> deleteFavCrypto(@PathVariable long id, HttpServletRequest request){
        Optional<Criptocurrency> cryptoOptional = criptocurrencyService.findById(id);
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Criptocurrency crypto = cryptoOptional.get();
            if (user.getCriptocurrencies().contains(crypto)){
                user.removeCript(crypto);
                crypto.setImage("images/starEmpty.svg");
            }
            userService.save(user);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(summary = "Get a list of the user's favCryptocurrency.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of favCryptocurrency is displayed correctly",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "List of users not found",
                    content = @Content
            )
    })
    @GetMapping("/favCryptocurrency")
    public Collection<Criptocurrency> getFavCryptocurrencies(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        User user = userOptional.get();
        return user.getCriptocurrencies();
    }

    /*@Operation(summary = "Delete a cryptocurrency by its id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "cryptocurrency deleted correctly",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid id supplied",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "cryptocurrency id not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Criptocurrency> deleteUser(@PathVariable long id){
        Optional<Criptocurrency> cryptoOptional = criptocurrencyService.findById(id);
        if (cryptoOptional.isPresent()){
            Criptocurrency crypto = cryptoOptional.get();
            criptocurrencyService.delete(crypto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

}
