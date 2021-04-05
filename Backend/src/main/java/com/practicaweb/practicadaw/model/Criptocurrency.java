package com.practicaweb.practicadaw.model;

import javax.persistence.*;

@Entity
public class Criptocurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCripto;
    @Column(length = 135, nullable = false)
    private String nameCripto;
    @Column(length = 135, nullable = false)
    private double priceCripto;
    @Column(length = 135, nullable = false)
    private String shortName;
    private String image;
//    @ManyToMany
//    private User user;

    public Criptocurrency() {
    }

    public Criptocurrency(long idCripto, String nameCripto, double priceCripto, String shortName, String image) {
        this.idCripto = idCripto;
        this.nameCripto = nameCripto;
        this.priceCripto = priceCripto;
        this.shortName = shortName;
        this.image = image;
    }

    public long getIdCripto() {
        return idCripto;
    }

    public void setIdCripto(long idCripto) {
        this.idCripto = idCripto;
    }

    public String getNameCripto() {
        return nameCripto;
    }

    public void setNameCripto(String nameCripto) {
        this.nameCripto = nameCripto;
    }

    public double getPriceCripto() {
        return priceCripto;
    }

    public void setPriceCripto(double priceCripto) {
        this.priceCripto = priceCripto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
