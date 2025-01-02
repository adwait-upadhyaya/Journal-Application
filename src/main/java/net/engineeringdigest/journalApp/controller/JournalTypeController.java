package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalType;
import net.engineeringdigest.journalApp.service.JournalTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal-type")
public class JournalTypeController {

    private final JournalTypeService journalTypeService;

    public JournalTypeController(JournalTypeService journalTypeService) {
        this.journalTypeService = journalTypeService;
    }

    @GetMapping
    public ResponseEntity<List<JournalType>> findAll() {
        return new ResponseEntity<>(journalTypeService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalType> addType(@RequestBody JournalType journalType) {
        return new ResponseEntity<>(journalTypeService.save(journalType), HttpStatus.CREATED);
    }
}
