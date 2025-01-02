package net.engineeringdigest.journalApp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.dto.JournalEntryDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryFlatDTO;
import net.engineeringdigest.journalApp.dto.JournalEntryProjectionDTO;
import net.engineeringdigest.journalApp.dto.JournalRequestDTO;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Slf4j
@Tag(name = "Journal Entries", description = "APIs for managing journal entries")
@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private final JournalEntryService journalService;

    public JournalEntryController(JournalEntryService journalService) {
        this.journalService = journalService;
        log.info("JournalEntryController initialized");
    }

    @Operation(summary = "Get all journal entries", description = "Fetches all journal entries as DTOs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all entries")
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<JournalEntryDTO>> findAll() {
        log.info("Fetching all journal entries");
        List<JournalEntryDTO> entries = journalService.findAll();
        log.debug("Retrieved {} journal entries", entries.size());
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @Operation(summary = "Get flat journal entries", description = "Fetches all journal entries in a flat format")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved flat entries")
    })
    @GetMapping("/flat")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<JournalEntryFlatDTO>> findAllFlat() {
        log.info("Fetching all journal entries in flat format");
        List<JournalEntryFlatDTO> entries = journalService.findAllFlat();
        log.debug("Retrieved {} flat journal entries", entries.size());
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }

    @Operation(summary = "Get entry summaries", description = "Fetches title and content summaries for all entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved summaries")
    })
    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<JournalEntryProjectionDTO>> findTitleAndContent() {
        log.info("Fetching journal entry summaries");
        List<JournalEntryProjectionDTO> summaries = journalService.findTitleAndContent();
        log.debug("Retrieved {} journal entry summaries", summaries.size());
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    @Operation(summary = "Find journal entry by ID", description = "Fetches a journal entry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Journal entry found"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @GetMapping("/id/{myId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<JournalEntry> findById(
            @Parameter(description = "ID of the journal entry to retrieve")
            @PathVariable Long myId
    ) {
        log.info("Fetching journal entry with ID: {}", myId);
        JournalEntry entry = journalService.findById(myId);
        log.debug("Retrieved journal entry: {}", entry.getTitle()); // Logging just the title for brevity
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @Operation(summary = "Add new journal entry", description = "Creates a new journal entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entry created successfully")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addEntry(
            @Parameter(description = "New journal entry to add")
            @Valid @RequestBody JournalRequestDTO newJournalEntry) {
        log.info("Adding new journal entry with title: {}", newJournalEntry.getTitle());
        journalService.addEntry(newJournalEntry);
        log.info("Successfully added new journal entry with title: {}", newJournalEntry.getTitle());
        return new ResponseEntity<>("Journal Entered Successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Delete journal entry", description = "Deletes a journal entry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @DeleteMapping("/id/{myId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(
            @Parameter(description = "ID of the entry to delete")
            @PathVariable Long myId) {
        log.info("Deleting journal entry with ID: {}", myId);
        journalService.deleteById(myId);
        log.info("Successfully deleted journal entry with ID: {}", myId);
        return new ResponseEntity<>("Entry Deleted Successfully", HttpStatus.OK);
    }

    @Operation(summary = "Update journal entry", description = "Updates a journal entry by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entry updated successfully"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @PutMapping("/id/{myId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateById(
            @Parameter(description = "ID of the entry to update")
            @PathVariable Long myId,
            @Parameter(description = "Updated journal entry data")
            @RequestBody JournalEntry updatedEntry) {
        log.info("Updating journal entry with ID: {}", myId);
        journalService.updateById(myId, updatedEntry);
        log.info("Successfully updated journal entry with ID: {}", myId);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }
}