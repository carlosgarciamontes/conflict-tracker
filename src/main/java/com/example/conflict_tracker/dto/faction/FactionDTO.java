package com.example.conflict_tracker.dto.faction;

import java.util.List;

public class FactionDTO {
    private Long id;
    private String name;
    private String conflictName; // Mostramos el nombre, es más útil que el ID
    private List<String> supporterCountryNames; // Lista plana de nombres de países

    public FactionDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getConflictName() { return conflictName; }
    public void setConflictName(String conflictName) { this.conflictName = conflictName; }

    public List<String> getSupporterCountryNames() { return supporterCountryNames; }
    public void setSupporterCountryNames(List<String> supporterCountryNames) { this.supporterCountryNames = supporterCountryNames; }
}