package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

@Controller
public class SettingsController {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages/");
    @Autowired
    UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) throws IOException {

        Principal principal = request.getUserPrincipal();


        if(principal != null) {

            Optional<User> user = userService.findByName(principal.getName());

            model.addAttribute("logged", true);
            model.addAttribute("userName", principal.getName());
            model.addAttribute("isAdmin", request.isUserInRole("ADMIN"));
            model.addAttribute("idUser", user.get().getIdUser());
            model.addAttribute("firstname", user.get().getFirstname());
            model.addAttribute("surname", user.get().getSurname());
            model.addAttribute("email", user.get().getEmail());


        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/settings")
    public String settings() {
        return "settings";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("firstname") String firstname, @RequestParam("surname") String surname, HttpServletRequest request) throws IOException {
        Principal principal = request.getUserPrincipal();
        User user = userService.findByName(principal.getName()).orElseThrow();
        if (!imageFile.isEmpty()){
            user.setImage(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        }
//        if (!imageFile.getOriginalFilename().equals("")) {
////            user.setImage(imageFile.getBytes());
////            Path imagePath = IMAGES_FOLDER.resolve(imageFile.getOriginalFilename());
////            user.setImage(userService.extractBytes(IMAGES_FOLDER + "defaultImage.jpg"));
////            imageFile.transferTo(imagePath);
////            user.setImage(imageFile.getOriginalFilename());
//        } else {
//            user.setImage(user.getImage());
//        }
        user.setFirstname(firstname);
        user.setSurname(surname);
        userService.save(user);
        return "redirect:/settings";
    }

    @PostMapping("/upload_image")
    public String uploadImage(@RequestParam String imageName, @RequestParam MultipartFile image) throws IOException {

        Files.createDirectories(IMAGES_FOLDER);

        Path imagePath = IMAGES_FOLDER.resolve("image.jpg");

        image.transferTo(imagePath);

        return "redirect:/settings";
    }

    @GetMapping("user/{idUser}/view_image")
    public ResponseEntity<Object> downloadImage(@PathVariable long idUser) throws SQLException{
        Optional<User> user = userService.findById(idUser);
        if (user.isPresent() && user.get().getImage() != null){
            Resource file = new InputStreamResource(user.get().getImage().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(user.get().getImage().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
