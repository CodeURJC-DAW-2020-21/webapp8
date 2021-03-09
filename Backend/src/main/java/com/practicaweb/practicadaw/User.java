package com.practicaweb.practicadaw;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    private Long id;
    private String username;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    public Long getId() {
        return id;
    }
}
