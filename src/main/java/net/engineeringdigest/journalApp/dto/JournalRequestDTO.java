package net.engineeringdigest.journalApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Schema(name = "JournalRequestDTO", description = "Request DTO for creating a journal entry")
public class JournalRequestDTO {

    @NotNull(message = "Title cannot be null")
    @Schema(description = "Title of the journal entry", example = "Journal on Java")
    private String title;

    @NotNull(message = "Content cannot be null")
    @Schema(description = "Content of the journal entry", example = "Today I learned about Spring Boot...")
    private String content;

    @NotNull(message = "Journal Type cannot be null")
    @Schema(description = "Journal Type with ID")
    private JournalTypeDTO journalType;

    @NotNull(message = "Written date cannot be null")
    @PastOrPresent(message = "Written date cannot be in the future")
    @Schema(description = "Date the journal was written", example = "2024-12-15T14:30:00")
    private Date writtenAt;

    @Getter
    @Setter
    public static class JournalTypeDTO {
        @NotNull(message = "Journal Type ID is required")
        @Schema(description = "Journal type identifier", example = "1")
        private Long id;
    }
}
