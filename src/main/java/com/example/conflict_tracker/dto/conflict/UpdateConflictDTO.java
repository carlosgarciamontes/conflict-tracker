package com.example.conflict_tracker.dto.conflict;

import java.util.List;

public class UpdateConflictDTO {
    private String name;
    private String status;
    private String description;
    private List<Long> countryIds;

    public UpdateConflictDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Long> getCountryIds() { return countryIds; }
    public void setCountryIds(List<Long> countryIds) { this.countryIds = countryIds; }
}