package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalType;

import java.util.List;

public interface IJournalTypeService {
    List<JournalType> findAll();
    JournalType findById(Long id);
    JournalType save(JournalType journalType);
}
