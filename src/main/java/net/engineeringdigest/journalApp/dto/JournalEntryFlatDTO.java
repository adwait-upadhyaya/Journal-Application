package net.engineeringdigest.journalApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Flat Data Transfer Object representing basic information of a journal entry
 */
@Data
@AllArgsConstructor
@Schema(name = "JournalEntryFlatDTO", description = "Flat DTO representing summarized journal entry data")
public class JournalEntryFlatDTO {

    @Schema(description = "Unique identifier of the journal entry", example = "201")
    private Long id;

    @Schema(description = "Title of the journal entry", example = "Daily Gratitude")
    private String title;

    @Schema(description = "Brief content or excerpt of the journal entry", example = "Grateful for the small things...")
    private String content;

    @Schema(description = "Date of the journal being written", example = "")
    private Date written_at;

    @Schema(description = "Name of the journal entry type", example = "Gratitude")
    private String typeName;
}
