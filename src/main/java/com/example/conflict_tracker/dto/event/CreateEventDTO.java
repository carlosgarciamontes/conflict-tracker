package com.example.conflict_tracker.dto.event;

import java.time.LocalDate;

public class CreateEventDTO {
    private LocalDate eventDate;
    private String location;
    private String description;
    private Long conflictId; // ID obligatorio para vincularlo

    public CreateEventDTO() {}

    // Getters y Setters
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getConflictId() { return conflictId; }
    public void setConflictId(Long conflictId) { this.conflictId = conflictId; }
}