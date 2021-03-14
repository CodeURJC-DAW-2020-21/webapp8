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

    public Criptocurrency() {
    }

    public long getIdCripto() {
        return idCripto;
    }

    public void setIdCripto(long idCripto) {
        this.idCripto = idCripto;
    }
}
