package com.practicaweb.practicadaw.repository;

import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    @Query(value = "DELETE FROM webapp8_bbdd.entry WHERE user_id_user = :idUser", nativeQuery = true)
    void deleteEntryByIdUser(@Param("idUser") long idUser);

    long findByTitle(String title);

}
