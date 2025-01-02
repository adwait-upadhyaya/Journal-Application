package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalType;
import net.engineeringdigest.journalApp.exception.NotFoundException;
import net.engineeringdigest.journalApp.repository.JournalTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalTypeService implements IJournalTypeService {

    private final JournalTypeRepository journalTypeRepository;

    public JournalTypeService(JournalTypeRepository journalTypeRepository) {
        this.journalTypeRepository = journalTypeRepository;
    }

    @Override
    public List<JournalType> findAll() {
        return journalTypeRepository.findAll();
    }

    @Override
    public JournalType findById(Long id) {
        return journalTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Journal Type not found for ID: " + id));
    }

    @Override
    public JournalType save(JournalType journalType) {
        return journalTypeRepository.save(journalType);
    }
}
