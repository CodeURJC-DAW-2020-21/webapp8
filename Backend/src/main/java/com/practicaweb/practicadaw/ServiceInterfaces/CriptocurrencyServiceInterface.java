package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;

import java.util.List;

public interface CriptocurrencyServiceInterface {
    Criptocurrency save (Criptocurrency criptocurrency);
    void deleteCriptoByIdCripto(long idCripto);
    List<Criptocurrency> selectAll ();
}
