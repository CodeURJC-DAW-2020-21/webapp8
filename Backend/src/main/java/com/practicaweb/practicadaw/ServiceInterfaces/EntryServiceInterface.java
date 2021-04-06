package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EntryServiceInterface {
    Entry save (Entry entry);
    Page<Entry> selectAll(Pageable page);
    Optional<Entry> findById(long id);
    void deleteEntryByIdUser(long idUser);
    long  findByTitle(String title);

}
