package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalTypeRepository extends JpaRepository<JournalType, Long> {
}
