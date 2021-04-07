package com.practicaweb.practicadaw.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Comment {

    public interface Basic{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Basic.class)
    private long idComment;
    @Column(length = 145, nullable = false)
    @JsonView(Basic.class)
    private String descriptionComment;
    @JsonView(Basic.class)
    private LocalDateTime registrationDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Entry entry;


    public Comment(){}

    public Comment(long idComment, String descriptionComment, LocalDateTime registrationDate, User user, Entry entry) {
        this.idComment = idComment;
        this.descriptionComment = descriptionComment;
        this.registrationDate = registrationDate;
        this.user = user;
        this.entry = entry;
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public String getDescriptionComment() {
        return descriptionComment;
    }

    public void setDescriptionComment(String descriptionComment) {
        this.descriptionComment = descriptionComment;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
