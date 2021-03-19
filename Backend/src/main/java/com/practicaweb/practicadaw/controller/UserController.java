package com.practicaweb.practicadaw.controller;

//import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
//
//    private List<User> userList = new ArrayList();
//
//    userList<>.add(new User("marcos", ))
//
//
    @GetMapping("/{Role}")
    public void userRole(@PathVariable String role, Model model){
        if (role.equals("admin")){
            //model.addAttribute(); añadir el rol de admin
        }
        else if (role.equals("registered")){
            //model.addAttribute(); añadir el rol de registrado
        }
        else{
            //model.addAttribute(); añadir el rol de NO registrado
        }


    }

//    @PostMapping("/guardarUsuarioActual")
//    public void saveUser (@RequestParam long idUser, String name, String surname, String nickname, String email, String password, String role, Date registrationDate, List<Comment> comments){
//        User user = userService.getUserOrNull(idUser);
//        user.setName(name);
//        ...
//    }



}
