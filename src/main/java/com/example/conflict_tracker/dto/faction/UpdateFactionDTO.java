package com.example.conflict_tracker.dto.faction;

import java.util.List;

public class UpdateFactionDTO {
    private String name;
    private List<Long> supporterCountryIds;
    // Generalmente no permitimos mover una facción de conflicto en un update simple,
    // por eso no incluyo conflictId aquí.

    public UpdateFactionDTO() {}

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Long> getSupporterCountryIds() { return supporterCountryIds; }
    public void setSupporterCountryIds(List<Long> supporterCountryIds) { this.supporterCountryIds = supporterCountryIds; }
}