package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.Criptocurrency;
import com.practicaweb.practicadaw.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CriptocurrencyRepository extends JpaRepository<Criptocurrency, Long> {
    Criptocurrency findByNameCripto(String nameCripto);
}
