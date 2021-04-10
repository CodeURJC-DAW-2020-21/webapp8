package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.CriptocurrencyServiceInterface;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.CriptocurrencyRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriptocurrencyService implements CriptocurrencyServiceInterface {

    private final CriptocurrencyRepository criptocurrencyRepository;


    public CriptocurrencyService(CriptocurrencyRepository criptocurrencyRepository) {
        this.criptocurrencyRepository = criptocurrencyRepository;
    }

    @Override
    public Criptocurrency save(Criptocurrency criptocurrency) {
        return null;
    }

    @Override
    public Optional<Criptocurrency> findById(long idCripto) {
        return criptocurrencyRepository.findById(idCripto);
    }

    @Override
    public void delete(Criptocurrency crypto){
        criptocurrencyRepository.delete(crypto);
    }

    @Override
    public List<Criptocurrency> selectAll() {
        return criptocurrencyRepository.findAll();
    }

    @Override
    public Criptocurrency findByNameCripto(String nameCripto) {
        return criptocurrencyRepository.findByNameCripto(nameCripto);
    }

    @Override
    public void deleteCriptoByIdCripto(long idCripto) {
        criptocurrencyRepository.deleteById(idCripto);
    }
}
