package com.practicaweb.practicadaw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Criptocurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCripto;
    private String nameCripto;
    private double priceCripto;

    public Criptocurrency() {
    }

    public Criptocurrency(long idCripto, String nameCripto, double priceCripto) {
        this.idCripto = idCripto;
        this.nameCripto = nameCripto;
        this.priceCripto = priceCripto;
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
}
