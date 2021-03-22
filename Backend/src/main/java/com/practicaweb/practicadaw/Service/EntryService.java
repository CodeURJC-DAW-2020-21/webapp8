package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.EntryServiceInterface;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.repository.EntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EntryService implements EntryServiceInterface {

    private final EntryRepository entryRepository;
    private final UserService userService;

    public EntryService(EntryRepository entryRepository, UserService userService) {
        this.entryRepository = entryRepository;
        this.userService = userService;
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
