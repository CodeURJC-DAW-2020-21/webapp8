/*
package com.practicaweb.practicadaw.controller;

import com.practicaweb.practicadaw.Service.UserService;
import com.practicaweb.practicadaw.auxClasses.AuxUser;
import com.practicaweb.practicadaw.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    @PostMapping("/forgotPassword")
    public String forgotPassword ( @RequestParam ("email") String email, @RequestParam ("password") String password, @RequestParam ("confirmPassword") String confirmPassword){
        User userNewPassword = userService.selectByEmail(email);
        if (AuxUser.verificationPassword(password, confirmPassword)){
            userNewPassword.setPassword(confirmPassword);
            userService.save(userNewPassword);
            return "redirect:/login";
        }
        else{
            return "loginError";
        }
    }

    @PostMapping("/sendEmail")
    public String sendEmail (@RequestParam ("email") String email){
        String sender = "forocoin.soporteoficial@gmail.com";
        String emailPass = "forocoin1";
        String destinatary = email;
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
            message.setText("Pinche aqui para reestablecer su contraseña:" + "https://localhost:8443/password)");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", sender, emailPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e){

        }
        return "redirect:/login";
    }
}
*/
