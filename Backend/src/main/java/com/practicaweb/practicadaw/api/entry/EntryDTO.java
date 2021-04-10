package com.practicaweb.practicadaw.api.entry;

import com.fasterxml.jackson.annotation.JsonView;
import com.practicaweb.practicadaw.model.Comment;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class EntryDTO {

    public interface EntryUpdate{}

    private long idEntry;
    private String title;
    private String description;
    private LocalDateTime registrationDate;
    private User user;

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
}
