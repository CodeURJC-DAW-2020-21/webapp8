package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.auxClasses.Auxiliar;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

    private final UserService userService;
    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages/");

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    // New user
    @PostMapping("/create_user")
    public String createUser (@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword) throws IOException {
        if (AuxUser.verificationPassword(user.getEncodedPassword(), confirmPassword)){
            user.setRegistrationDate(LocalDateTime.now());
            user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
            List<String> roles = new ArrayList<>();
            roles.add("USER");
            user.setRoles(roles);
            userService.setUserImage(user, "static/profileImages/defaultImage.jpg");
            userService.save(user);
            return "redirect:/login";
        }
        else {
            return "redirect:/register";
        }
    }
}
