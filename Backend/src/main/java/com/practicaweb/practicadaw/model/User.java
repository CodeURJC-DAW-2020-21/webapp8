package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(length = 135, nullable = false)
    private String name;
    @Column(length = 135, nullable = false)
    private String surname;
    @Column(length = 45, nullable = false, unique = true)
    private String nickname;
    @Column(length = 135, unique = true)
    private String email;
    @Column(length = 45, nullable = false)
    private String password;
    @Column(length = 45, nullable = false)
    private String role;
    private Date registrationDate;
    @OneToMany
    private List<Comment> comments;

    public User() {}

    public User(long idUser, String name, String surname, String nickname, String email, String password, String role, Date registrationDate, List<Comment> comments) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
        this.comments = comments;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}
