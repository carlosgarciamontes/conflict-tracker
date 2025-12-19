package com.example.conflict_tracker.dto.event;

import java.time.LocalDate;

public class UpdateEventDTO {
    private LocalDate eventDate;
    private String location;
    private String description;

    public UpdateEventDTO() {}

    // Getters y Setters
    public LocalDate getEventDate() { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}