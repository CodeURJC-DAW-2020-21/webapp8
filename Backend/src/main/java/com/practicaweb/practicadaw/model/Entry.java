package com.practicaweb.practicadaw.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Entry {

    public interface Basic{}
    public interface Comments{}
    public interface EntryUser{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Basic.class)
    private long idEntry;
    @Column(length = 200, nullable = false)
    @JsonView(Basic.class)
    private String title;
    @Column(length = 500, nullable = false)
    @JsonView(Basic.class)
    private String description;
    @JsonView(Basic.class)
    private LocalDateTime registrationDate;
    @ManyToOne
    @JsonView(EntryUser.class)
    private User user;
    @OneToMany(mappedBy = "entry", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonView(Comments.class)
    private List<Comment> comment;

    public Entry() {
    }

    public Entry(long idEntry, String title, String description, LocalDateTime registrationDate, User user) {
        this.idEntry = idEntry;
        this.title = title;
        this.description = description;
//        this.number_of_visits = number_of_visits;
        this.registrationDate = registrationDate;
        this.user = user;
//        this.comment = comment;
    }

    public long getIdEntry() {
        return idEntry;
    }

    public void setIdEntry(long idEntry) {
        this.idEntry = idEntry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }
}
