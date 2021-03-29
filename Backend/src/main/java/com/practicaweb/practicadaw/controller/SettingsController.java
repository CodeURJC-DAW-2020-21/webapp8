package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Optional;

@Controller
public class SettingsController {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");
    @Autowired
    UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();


        if(principal != null) {

            User user = userService.findByName(principal.getName());

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));
            model.addAttribute("firstname", user.getFirstname());
            model.addAttribute("surname", user.getSurname());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("imageFile", user.getImage());


        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/update_user")
    public String updateUser(@ModelAttribute User userUpdate, @RequestParam("imageFile") MultipartFile imageFile, @RequestParam("firtsname") String firstname, @RequestParam("surname") String surname, HttpServletRequest request) throws IOException {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName());
        if (!imageFile.getOriginalFilename().equals("")) {
            Path imagePath = IMAGES_FOLDER.resolve(imageFile.getOriginalFilename());
            imageFile.transferTo(imagePath);
            userUpdate.setImage(imageFile.getOriginalFilename());
        } else {
            userUpdate.setImage(user.getImage());
        }
        user.setFirstname(firstname);
        user.setSurname(surname);
        userService.save(user);
        return "redirect:/settings";
    }
}
