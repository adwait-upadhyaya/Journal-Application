package net.engineeringdigest.journalApp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    @NotNull(message = "Username is required")
    private String username;

    @NotNull(message = "Password is Required")
    private String password;
}
