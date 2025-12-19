package com.example.conflict_tracker.dto.conflict;

import java.time.LocalDate;
import java.util.List;

public class ConflictDTO {
    private Long id;
    private String name;
    private String status;
    private LocalDate startDate;
    private String description;
    private List<String> countryNames;
    private int numberOfFactions;

    public ConflictDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getCountryNames() { return countryNames; }
    public void setCountryNames(List<String> countryNames) { this.countryNames = countryNames; }
    public int getNumberOfFactions() { return numberOfFactions; }
    public void setNumberOfFactions(int numberOfFactions) { this.numberOfFactions = numberOfFactions; }
}