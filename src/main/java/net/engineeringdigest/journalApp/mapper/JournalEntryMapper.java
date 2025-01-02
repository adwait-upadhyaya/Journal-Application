package net.engineeringdigest.journalApp.mapper;

import net.engineeringdigest.journalApp.dto.*;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JournalEntryMapper {

    @Mapping(target = "journalType", source = "journalType")
    JournalEntryDTO toDTO(JournalEntry entry);

    @Mapping(target = "typeName", source = "journalType.typeName")
    JournalEntryFlatDTO toFlatDTO(JournalEntry entry);

    List<JournalEntryDTO> toDTOList(List<JournalEntry> entries);
    List<JournalEntryFlatDTO> toFlatList(List<JournalEntry> entries);
}