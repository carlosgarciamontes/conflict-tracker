package com.example.conflict_tracker.controller;

import com.example.conflict_tracker.dto.country.CountryDTO;
import com.example.conflict_tracker.dto.country.CreateCountryDTO;
import com.example.conflict_tracker.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@CrossOrigin(origins = "*")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CreateCountryDTO createDTO) {
        return new ResponseEntity<>(countryService.createCountry(createDTO), HttpStatus.CREATED);
    }
}