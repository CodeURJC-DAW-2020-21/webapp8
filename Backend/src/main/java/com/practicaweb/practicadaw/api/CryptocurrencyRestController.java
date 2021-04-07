package com.practicaweb.practicadaw.api;

import com.practicaweb.practicadaw.Service.CriptocurrencyService;
import com.practicaweb.practicadaw.model.Criptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/cryptocurrencies/")
public class CryptocurrencyRestController {

    @Autowired
    CriptocurrencyService criptocurrencyService;

    @GetMapping("/")
    public Collection<Criptocurrency> getCryptocurrencies(){
        return criptocurrencyService.selectAll();
    }

//    To create objects with postMapping we need to use: @RequestBody Criptocurrency criptocurrency
//    @PostMapping

}
