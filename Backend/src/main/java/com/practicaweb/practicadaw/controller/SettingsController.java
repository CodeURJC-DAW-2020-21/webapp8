package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
public class SettingsController {

    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {


        Principal principal = request.getUserPrincipal();

        if(principal != null) {

            Optional<User> user = userRepository.findByName(principal.getName());

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));
            /*model.addAttribute("surname", user.getSurname());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("nickname", user.getUserName());
            model.addAttribute("imageFile", user.getImage());*/


        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

}
