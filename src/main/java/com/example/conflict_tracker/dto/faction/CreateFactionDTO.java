package com.example.conflict_tracker.dto.faction;

import java.util.List;

public class CreateFactionDTO {
    private String name;
    private Long conflictId; // Necesitamos el ID para saber a qué conflicto pertenece
    private List<Long> supporterCountryIds; // IDs de los países que apoyan

    public CreateFactionDTO() {}

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }

    public List<Long> getSupporterCountryIds() { return supporterCountryIds; }
    public void setSupporterCountryIds(List<Long> supporterCountryIds) { this.supporterCountryIds = supporterCountryIds; }
}