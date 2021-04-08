package com.practicaweb.practicadaw.api.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.multipart.MultipartFile;

public class UserDTO {

    public interface Update{}

    private String idUser;
    private String encodedPassword;
    private String name;
    @JsonView(Update.class)
    private String surname;
    @JsonView(Update.class)
    private String firstname;
    private String email;
    @JsonView(Update.class)
    private MultipartFile image;


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

}
