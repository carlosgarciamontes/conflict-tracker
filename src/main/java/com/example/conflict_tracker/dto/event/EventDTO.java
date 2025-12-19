package com.example.conflict_tracker.dto.event;

import java.time.LocalDate;

public class EventDTO {
    private Long id;
    private LocalDate eventDate;
    private String location;
    private String description;
    private String conflictName; // Nombre del conflicto asociado

    public EventDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getConflictName() { return conflictName; }
    public void setConflictName(String conflictName) { this.conflictName = conflictName; }
}