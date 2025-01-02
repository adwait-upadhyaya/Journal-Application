package net.engineeringdigest.journalApp.exception;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.error = error;
        this.message = message;
        this.path = path;
    }
}