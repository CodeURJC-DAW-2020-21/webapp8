package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.CriptocurrencyServiceInterface;
import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.repository.CriptocurrencyRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriptocurrencyService implements CriptocurrencyServiceInterface {

    private final CriptocurrencyRepository criptocurrencyRepository;
    private final UserRepository userRepository;


    public CriptocurrencyService(CriptocurrencyRepository criptocurrencyRepository, UserRepository userRepository) {
        this.criptocurrencyRepository = criptocurrencyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Criptocurrency save(Criptocurrency criptocurrency) {
        return null;
    }

    @Override
    public List<Criptocurrency> selectAll() {
        return criptocurrencyRepository.findAll();
    }

    @Override
    public void deleteCriptoByIdCripto(long idCripto) {
        criptocurrencyRepository.deleteById(idCripto);
    }
}
