package com.example.conflict_tracker.dto.country;

public class CreateCountryDTO {
    private String name;
    private String code;

    public CreateCountryDTO() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}