package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime registrationDate;
    @Lob
    private Blob image;
    private String tokenPass;
    private LocalDateTime tokenCreationDate;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Comment> comments;
    @ManyToMany
    private List<Criptocurrency> criptocurrencies;
    @ManyToMany
    private List<User> friends;
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Entry> entries;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    public User(long idUser, String encodedPassword, String name, String surname, String firstname, String email, LocalDateTime registrationDate, List<Criptocurrency> criptocurrencies, String... roles) {
        this.idUser = idUser;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.surname = surname;
        this.firstname = firstname;
        this.email = email;
        this.registrationDate = registrationDate;
//        this.image = image;
        this.roles = List.of(roles);
        this.criptocurrencies = criptocurrencies;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
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

    public List<Criptocurrency> getCriptocurrencies() {
        return criptocurrencies;
    }

    public void setCriptocurrencies(List<Criptocurrency> cryptocurrencies) {
        this.criptocurrencies = cryptocurrencies;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addCript(Criptocurrency criptocurrency){
        criptocurrencies.add(criptocurrency);
    }

    public void removeCript(Criptocurrency criptocurrency){
        criptocurrencies.remove(criptocurrency);
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public void removeFriend(User user){
        friends.remove(user);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public String getTokenPass() {
        return tokenPass;
    }

    public void setTokenPass(String tokenPass) {
        this.tokenPass = tokenPass;
    }

    public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
        this.tokenCreationDate = tokenCreationDate;
    }

    public LocalDateTime getTokenCreationDate() {
        return tokenCreationDate;
    }
}
