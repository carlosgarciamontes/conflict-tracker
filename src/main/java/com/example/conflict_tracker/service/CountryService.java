package com.example.conflict_tracker.service;

import com.example.conflict_tracker.dto.country.CountryDTO;
import com.example.conflict_tracker.dto.country.CreateCountryDTO;
import com.example.conflict_tracker.model.Country;
import com.example.conflict_tracker.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> dtos = new ArrayList<>();
        for (Country c : countries) {
            CountryDTO dto = new CountryDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dto.setCode(c.getCode());
            dtos.add(dto);
        }
        return dtos;
    }

    public CountryDTO createCountry(CreateCountryDTO createDTO) {
        Country country = new Country();
        country.setName(createDTO.getName());
        country.setCode(createDTO.getCode());

        Country saved = countryRepository.save(country);

        CountryDTO dto = new CountryDTO();
        dto.setId(saved.getId());
        dto.setName(saved.getName());
        dto.setCode(saved.getCode());
        return dto;
    }
}