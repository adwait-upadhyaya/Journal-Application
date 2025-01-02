package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.dto.JournalEntryDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryFlatDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryProjectionDTO;
import net.engineeringdigest.journalApp.dto.JournalRequestDTO;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import java.util.List;

public interface IJournalEntryService {
    List<JournalEntryDTO> findAll();
    List<JournalEntryFlatDTO> findAllFlat();
    JournalEntry findById(Long id);
    List<JournalEntryProjectionDTO> findTitleAndContent();
    void addEntry(JournalRequestDTO entry);
    boolean deleteById(Long id);
    boolean updateById(Long id, JournalEntry updatedEntry);
}
