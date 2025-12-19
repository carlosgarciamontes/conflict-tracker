package com.example.conflict_tracker.dto.country;

public class CountryDTO {
    private Long id;
    private String name;
    private String code;

    public CountryDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}