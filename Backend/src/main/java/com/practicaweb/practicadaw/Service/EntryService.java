package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.EntryServiceInterface;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class EntryService implements EntryServiceInterface {

    private final EntryRepository entryRepository;
    private final UserRepository userRepository;
    private User user1;

    public EntryService(EntryRepository entryRepository, UserRepository userRepository) {
        this.entryRepository = entryRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

    @Override
    public Page<Entry> selectAll(Pageable page) {
        return entryRepository.findAll(page);
    }

    @Override
    public Optional<Entry> findById(long idEntry) {
        return entryRepository.findById(idEntry);
    }

    @Override
    @Transactional
    public void deleteEntryByIdUser(long idUser) {
        entryRepository.deleteEntryByIdUser(idUser);
    }

    @Override
    @Transactional
    public long findByTitle(String title) {
        return entryRepository.findByTitle(title);
    }

}
