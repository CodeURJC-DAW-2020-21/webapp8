package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    private String encodedPassword;
    @Column(length = 135, nullable = false)
    private String name;
    @Column(length = 135, nullable = false)
    private String surname;
    @Column(length = 45, nullable = false, unique = true)
    private String userName;
    @Column(length = 135, unique = true)
    private String email;
    @Column(length = 45, nullable = false)
    private Date registrationDate;
    @Column(length = 45, nullable = false)
    private String image;
    @OneToMany
    private List<Comment> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    public User(long idUser, String name, String surname, String userName, String email, Date registrationDate, String image, List<Comment> comments, String encodedPassword, String... roles) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.image = image;
        this.comments = comments;
        this.encodedPassword = encodedPassword;
        this.roles = List.of(roles);
    }

    public User() {
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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

    public String getUserName() { return userName; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
