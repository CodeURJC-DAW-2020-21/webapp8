package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.EntryServiceInterface;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.model.User;
import com.practicaweb.practicadaw.repository.EntryRepository;
import com.practicaweb.practicadaw.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;


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
    public List<Entry> selectAll() {
        return entryRepository.findAll();
    }

    @Override
    public Entry selectById(long id) {
        return entryRepository.getOne(id);
    }

    @Override
    @Transactional
    public void deleteEntryByIdUser(long idUser) {
        entryRepository.deleteEntryByIdUser(idUser);
    }

}
