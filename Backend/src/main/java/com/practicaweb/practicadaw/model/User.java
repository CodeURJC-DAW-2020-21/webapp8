package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idUser;
    private String encodedPassword;
    @Column(length = 135, nullable = false, unique = true)
    private String name;
    @Column(length = 135, nullable = false)
    private String surname;
    @Column(length = 45, nullable = false)
    private String firstname;
    @Column(length = 135, nullable = false)
    private String email;
    @Column(length = 45, nullable = false)
    private Date registrationDate;
    @Column(length = 45, nullable = false)
    private String image;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Criptocurrency> cryptocurrencies;
    @OneToMany
    private List<User> friends;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    public User(long idUser, String encodedPassword, String name, String surname, String firstname, String email, Date registrationDate, String image, String... roles) {
        this.idUser = idUser;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.surname = surname;
        this.firstname = firstname;
        this.email = email;
        this.registrationDate = registrationDate;
        this.image = image;
        this.roles = List.of(roles);
    }

    public User() {
    }

    public List<Criptocurrency> getCryptocurrencies() {
        return cryptocurrencies;
    }

    public void setCryptocurrencies(List<Criptocurrency> cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
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

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public void setRoles(String... roles) {
        this.roles = List.of(roles);
    }


    public void addFriend(User user){
        friends.add(user);
    }

    public void addCript(Criptocurrency criptocurrency){
        cryptocurrencies.add(criptocurrency);
    }
}
