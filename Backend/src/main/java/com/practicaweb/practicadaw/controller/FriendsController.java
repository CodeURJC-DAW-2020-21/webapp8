package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.CommentService;
import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.Service.EntryService;
import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class FriendsController {

    @Autowired
    UserService userService;

    @Autowired
    EntryService entryService;

//    @PostMapping("/add_friend")
//    public String add_friend(@RequestParam long idUser, HttpServletRequest request) {
//        Principal principal = request.getUserPrincipal();
//        if (principal != null) {
//            User user = userService.findByName(principal.getName());
//            User friend = userService.selectById(idUser).orElseThrow();
//            if (user.getFriends().contains(friend)) {
//                user.removeFriend(friend);
//            } else {
//                user.addFriend(friend);
//            }
//            userService.save(user);
//        }
//        return "redirect:/";
//    }
}
