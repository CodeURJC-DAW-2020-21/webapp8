//package com.practicaweb.practicadaw.controller;
//
//import com.practicaweb.practicadaw.model.Comment;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class CommentController {
//    @PostMapping("/guardarDiscussion")
//    public String guardarComentario(Model model, Comment comentario){
//        model.addAttribute("comentario", comentario);
//
//        return "pruebaComentario";
//    }
//    public String guardarComentario(Model model, @RequestParam String testTitle,  @RequestParam String testDescription){
//        model.addAttribute("description", testDescription);
//        model.addAttribute("titulo", testTitle);
//
//        return "pruebaComentario";
//    }
//}
