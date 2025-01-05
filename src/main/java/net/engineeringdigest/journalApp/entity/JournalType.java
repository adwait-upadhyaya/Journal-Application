package net.engineeringdigest.journalApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "JOURNAL_TYPE")
@Data
public class JournalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
