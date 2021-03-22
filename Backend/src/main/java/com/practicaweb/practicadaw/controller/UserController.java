package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {
    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/updateUser")
    public String updateUser(@ModelAttribute User userUpdate, @RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) throws IOException {
        Path imagePath = IMAGES_FOLDER.resolve(imageFile.getOriginalFilename());
        imageFile.transferTo(imagePath);
        userUpdate.setImage(imageFile.getOriginalFilename());
        User oldUser = userService.selectByEmail(userUpdate.getEmail());
        userUpdate.setIdUser(oldUser.getIdUser());
        userUpdate.setPassword(oldUser.getPassword());
        userUpdate.setEmail(oldUser.getEmail());
        userUpdate.setRole(oldUser.getRole());
        userUpdate.setRegistrationDate(oldUser.getRegistrationDate());
        userService.save(userUpdate);
        HttpSession mysession = request.getSession(true);
        mysession.removeAttribute("actualUser");
        mysession.setAttribute("actualUser",userUpdate);
        return "redirect:/";
    }
}
