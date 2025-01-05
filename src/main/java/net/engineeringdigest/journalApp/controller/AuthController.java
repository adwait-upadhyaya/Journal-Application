package net.engineeringdigest.journalApp.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.dto.AuthRequestDTO;
import net.engineeringdigest.journalApp.dto.AuthResponseDTO;
import net.engineeringdigest.journalApp.dto.RegisterRequest;
import net.engineeringdigest.journalApp.dto.UserDto;
import net.engineeringdigest.journalApp.exception.UserAlreadyExistsException;
import net.engineeringdigest.journalApp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request) throws UserAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }
}