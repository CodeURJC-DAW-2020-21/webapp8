package com.practicaweb.practicadaw.Service;

import com.practicaweb.practicadaw.ServiceInterfaces.EntryServiceInterface;
import com.practicaweb.practicadaw.model.Entry;
import com.practicaweb.practicadaw.repository.EntryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EntryService implements EntryServiceInterface {

    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    @Transactional
    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

}
