package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.dto.JournalEntryDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryFlatDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryProjectionDTO;
import net.engineeringdigest.journalApp.dto.JournalRequestDTO;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.JournalType;
import net.engineeringdigest.journalApp.exception.ErrorCode;
import net.engineeringdigest.journalApp.exception.NotFoundException;
import net.engineeringdigest.journalApp.mapper.JournalEntryMapper;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.JournalTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JournalEntryService implements IJournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalTypeRepository journalTypeRepository;
    private final JournalEntryMapper journalEntryMapper;

    public JournalEntryService(JournalEntryRepository journalEntryRepository,
                               JournalTypeRepository journalTypeRepository,
                               JournalEntryMapper journalEntryMapper) {
        this.journalEntryRepository = journalEntryRepository;
        this.journalTypeRepository = journalTypeRepository;
        this.journalEntryMapper = journalEntryMapper;
        log.info("JournalEntryService initialized");
    }

    @Override
    public List<JournalEntryDTO> findAll() {
        log.debug("Fetching all journal entries");
        List<JournalEntry> entries = journalEntryRepository.findAll();
        List<JournalEntryDTO> dtos = journalEntryMapper.toDTOList(entries);
        log.debug("Retrieved {} journal entries", entries.size());
        return dtos;
    }

    @Override
    public List<JournalEntryFlatDTO> findAllFlat() {
        log.debug("Fetching all journal entries in flat format");
        List<JournalEntry> entries = journalEntryRepository.findAll();
        List<JournalEntryFlatDTO> flatDtos = journalEntryMapper.toFlatList(entries);
        log.debug("Retrieved {} flat journal entries", entries.size());
        return flatDtos;
    }

    @Override
    public List<JournalEntryProjectionDTO> findTitleAndContent() {
        log.debug("Fetching journal entry titles and content");
        List<JournalEntryProjectionDTO> projections = journalEntryRepository.findTitleAndContent();
        log.debug("Retrieved {} journal entry projections", projections.size());
        return projections;
    }

    @Override
    public JournalEntry findById(Long id) {
        log.debug("Fetching journal entry with ID: {}", id);
        JournalEntry entry = journalEntryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Journal Entry not found for ID: {}", id);
                    return new NotFoundException("Journal Entry not found for ID: " + id, ErrorCode.RESOURCE_NOT_FOUND);
                });
        log.debug("Retrieved journal entry: {}", entry.getTitle());
        return entry;
    }

    @Override
    public void addEntry(JournalRequestDTO newJournalEntry) {
        log.info("Adding new journal entry with title: {}", newJournalEntry.getTitle());

        try {
            // Find Journal Type by ID
            JournalType journalType = journalTypeRepository.findById(newJournalEntry.getJournalType().getId())
                    .orElseThrow(() -> {
                        log.error("Journal Type not found for ID: {}", newJournalEntry.getJournalType().getId());
                        return new NotFoundException("Journal Type not found for ID: " +
                                newJournalEntry.getJournalType().getId());
                    });

            // Map DTO to Entity
            JournalEntry entry = new JournalEntry();
            entry.setTitle(newJournalEntry.getTitle());
            entry.setContent(newJournalEntry.getContent());
            entry.setJournalType(journalType);

            // Save the entry
            journalEntryRepository.save(entry);
            log.info("Successfully added new journal entry with title: {}", newJournalEntry.getTitle());
        } catch (Exception e) {
            log.error("Error adding journal entry: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        log.info("Attempting to delete journal entry with ID: {}", id);
        if (!journalEntryRepository.existsById(id)) {
            log.error("Cannot delete. Journal Entry not found for ID: {}", id);
            throw new NotFoundException("Cannot delete. Journal Entry not found for ID: " + id);
        }
        try {
            journalEntryRepository.deleteById(id);
            log.info("Successfully deleted journal entry with ID: {}", id);
            return true;
        } catch (Exception e) {
            log.error("Error deleting journal entry with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateById(Long id, JournalEntry updatedEntry) {
        log.info("Attempting to update journal entry with ID: {}", id);
        if (!journalEntryRepository.existsById(id)) {
            log.error("Cannot update. Journal Entry not found for ID: {}", id);
            throw new NotFoundException("Cannot update. Journal Entry not found for ID: " + id);
        }
        try {
            updatedEntry.setId(id);
            journalEntryRepository.save(updatedEntry);
            log.info("Successfully updated journal entry with ID: {}", id);
            return true;
        } catch (Exception e) {
            log.error("Error updating journal entry with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
}