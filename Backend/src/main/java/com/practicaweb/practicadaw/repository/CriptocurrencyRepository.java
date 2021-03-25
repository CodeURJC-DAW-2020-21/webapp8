package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.Criptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CriptocurrencyRepository extends JpaRepository<Criptocurrency, Long> {

}
