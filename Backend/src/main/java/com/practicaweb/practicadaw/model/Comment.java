package com.practicaweb.practicadaw.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComment;
    @Column(length = 145, nullable = false)
    private String descriptionComment;
    private Date registrationDate;
    @OneToOne
    private User user;


    public Comment(){}

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
}
