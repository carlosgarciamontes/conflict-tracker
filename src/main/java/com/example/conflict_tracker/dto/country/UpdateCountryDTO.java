package com.example.conflict_tracker.dto.country;

public class UpdateCountryDTO {
    private String name;
    private String code;

    public UpdateCountryDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}