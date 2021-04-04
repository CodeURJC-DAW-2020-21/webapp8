package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;

import java.util.List;
import java.util.Optional;

public interface EntryServiceInterface {
    Entry save (Entry entry);
    List<Entry> selectAll ();
    Optional<Entry> findById(long id);
    void deleteEntryByIdUser(long idUser);
    long  findByTitle(String title);

}
