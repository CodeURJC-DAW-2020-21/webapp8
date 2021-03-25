package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Criptocurrency;

public interface CriptocurrencyServiceInterface {
    Criptocurrency save (Criptocurrency criptocurrency);
    void deleteCriptoByIdCripto(long idCripto);
}
