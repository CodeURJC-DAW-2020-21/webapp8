package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @PostMapping("/guardarDiscussion")
    public String guardarComentario(@RequestParam String testTitle,  @RequestParam String testDescription){
        Comment comment = new Comment();
        //comment.setUser();
        comment.setTitle(testTitle);
        comment.setDescription(testDescription);
        //comment.setRegistrationDate();
        return "pruebaComentario";

//    public String guardarComentario(Model entity, Comment comentario){
//        entity.addAttribute("comentario", comentario);
//
//        return "pruebaComentario";
//    }
    }
}
