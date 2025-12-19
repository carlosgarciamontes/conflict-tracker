package com.example.conflict_tracker.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    @ManyToMany(mappedBy = "countries")
    private Set<Conflict> conflicts = new HashSet<>();

    @ManyToMany(mappedBy = "supporters")
    private Set<Faction> supportedFactions = new HashSet<>();

    // Constructor vacío
    public Country() {}

    // Constructor con campos básicos
    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Set<Conflict> getConflicts() { return conflicts; }
    public void setConflicts(Set<Conflict> conflicts) { this.conflicts = conflicts; }

    public Set<Faction> getSupportedFactions() { return supportedFactions; }
    public void setSupportedFactions(Set<Faction> supportedFactions) { this.supportedFactions = supportedFactions; }
}