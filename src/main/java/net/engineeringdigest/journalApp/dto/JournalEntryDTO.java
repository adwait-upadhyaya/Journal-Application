package net.engineeringdigest.journalApp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Data Transfer Object representing detailed information of a journal entry
 */
@Getter
@Setter
@AllArgsConstructor
@Schema(name = "JournalEntryDTO", description = "Detailed view of a journal entry with type information")
public class JournalEntryDTO {

    @Schema(description = "Unique identifier of the journal entry", example = "101")
    private Long id;

    @Schema(description = "Title of the journal entry", example = "Reflecting on the Week")
    private String title;

    @Schema(description = "Content of the journal entry", example = "This week was very productive and full of insights...")
    private String content;

    @Schema(description = "Date of the journal being written", example = "")
    private Date written_at;

    @Schema(description = "Type of journal entry with its identifier and name")
    private JournalTypeDTO journalType;

    @Getter
    @Setter
    @AllArgsConstructor
    @Schema(name = "JournalTypeDTO", description = "DTO representing journal type information")
    public static class JournalTypeDTO {

        @Schema(description = "Unique identifier of the journal type", example = "5")
        private Long id;

        @Schema(description = "Name of the journal type", example = "Reflection")
        private String typeName;
    }
}
