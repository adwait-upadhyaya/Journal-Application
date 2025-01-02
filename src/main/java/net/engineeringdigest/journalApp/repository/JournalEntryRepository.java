package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.dto.JournalEntryProjectionDTO;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {

    @Query("SELECT new net.engineeringdigest.journalApp.dto.JournalEntryProjectionDTO(j.title, j.content) FROM JournalEntry j")
    List<JournalEntryProjectionDTO> findTitleAndContent();
}
