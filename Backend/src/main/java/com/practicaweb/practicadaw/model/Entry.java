package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntry;
    @Column(length = 200, nullable = false)
    private String title;
    @Column(length = 500, nullable = false)
    private String description;
    @Column(length = 45, nullable = false)
    private int number_of_visits;
    private Date registrationDate;
    @OneToOne
    private User user;
    @OneToMany
    private List<Comment> comment;

    public int getNumber_of_visits() {
        return number_of_visits;
    }

    public void setNumber_of_visits(int number_of_visits) {
        this.number_of_visits = number_of_visits;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
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
