package com.practicaweb.practicadaw.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.api.user.UserRestController;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
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

    @GetMapping("/")
    public Collection<Criptocurrency> getCryptocurrencies(){
        return criptocurrencyService.selectAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Criptocurrency> getCriptocurrency(@PathVariable long id){
        Optional<Criptocurrency> cripto = criptocurrencyService.findById(id);

        return cripto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

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


    @GetMapping("/favCryptocurrency")
    public Collection<Criptocurrency> getFavCryptocurrencies(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        Optional<User> userOptional = userService.findByName(principal.getName());
        User user = userOptional.get();
        return user.getCriptocurrencies();
    }

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
    }

}
