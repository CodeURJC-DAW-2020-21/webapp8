package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CriptocurrencyController {

    @Autowired
    UserService userService;

    private final CriptocurrencyService criptocurrencyService;

    public CriptocurrencyController(CriptocurrencyService criptocurrencyService) {
        this.criptocurrencyService = criptocurrencyService;
    }

    @PostMapping("/add_cripto")
    public String add_criptocurrency(@RequestParam String nameCripto, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = userService.findByName(principal.getName());
            Criptocurrency cripto = criptocurrencyService.findByNameCripto(nameCripto); //¿Este serie el id de la persona que queremos seguir?
            if (user.getCriptocurrencies().contains(cripto)) {
                user.removeCript(cripto);
            } else {
                user.addCript(cripto);
            }
            userService.save(user);
        }
        return "redirect:/criptomonedas";
    }

}
