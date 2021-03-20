package com.practicaweb.practicadaw.ServiceInterfaces;

import com.practicaweb.practicadaw.model.Entry;

import java.util.List;

public interface EntryServiceInterface {
    Entry save (Entry entry);

    List<Entry> selectAll ();
}
