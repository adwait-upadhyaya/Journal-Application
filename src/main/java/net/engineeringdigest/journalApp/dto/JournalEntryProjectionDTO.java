package net.engineeringdigest.journalApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Projection DTO representing summarized journal entry data (title and content only)
 */
@Data
@AllArgsConstructor
@Schema(name = "JournalEntryProjectionDTO", description = "Projection DTO for displaying journal title and content only")
public class JournalEntryProjectionDTO {

    @Schema(description = "Title of the journal entry", example = "Mindfulness Practice")
    private String title;

    @Schema(description = "Content of the journal entry", example = "Focused on mindfulness exercises today...")
    private String content;
}
