package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;

import java.util.List;
import java.util.Optional;

public interface CriptocurrencyServiceInterface {
    Criptocurrency save (Criptocurrency criptocurrency);
    void deleteCriptoByIdCripto(long idCripto);
    List<Criptocurrency> selectAll ();
    Optional<Criptocurrency> findById(long idCripto);
    Criptocurrency findByNameCripto (String nameCripto);
    void delete(Criptocurrency crypto);
}
