package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Controller
public class ForgotPassController {

    private final UserService userService;

    public ForgotPassController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    private String tokenPass;

    @PostMapping("/forgot_password")
    public String forgotPassword (@RequestParam ("password") String password, @RequestParam ("confirmPassword") String confirmPassword){
        userService.resetPassword(tokenPass, password);
        return "redirect:/login";
    }

    @PostMapping("/send_email")
    public String sendEmail (@RequestParam ("email") String email){
        String sender = "forocoin.soporteoficial@gmail.com";
        String emailPass = "forocoin1";
        String destinatary = email;
        String response = userService.forgotPassword(email);
        if (!response.startsWith("Invalid")){
            tokenPass = response;
            response = "https://localhost:8443/password?tokenPass=" + response;
        }
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", sender);
        properties.put("mail.smtp.clave", emailPass);
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try{
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatary));
            message.setSubject("Reestablecer contraseña de foroCoin");
            message.setText("Pinche aqui para reestablecer su contraseña:" + response);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", sender, emailPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e){

        }
        return "redirect:/login";
    }
}

