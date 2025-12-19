package com.example.conflict_tracker.service;

import com.example.conflict_tracker.dto.conflict.*;
import com.example.conflict_tracker.model.Conflict;
import com.example.conflict_tracker.model.Country;
import com.example.conflict_tracker.model.enums.ConflictStatus;
import com.example.conflict_tracker.repository.ConflictRepository;
import com.example.conflict_tracker.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConflictService {

    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    // Inyección por constructor
    public ConflictService(ConflictRepository conflictRepository, CountryRepository countryRepository) {
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    public List<ConflictDTO> getAllConflicts() {
        List<Conflict> conflicts = conflictRepository.findAll();
        List<ConflictDTO> dtos = new ArrayList<>();
        for (Conflict conflict : conflicts) {
            dtos.add(mapToDTO(conflict));
        }
        return dtos;
    }

    public List<ConflictDTO> getConflictsByStatus(String status) {
        ConflictStatus enumStatus = ConflictStatus.valueOf(status.toUpperCase());
        List<Conflict> conflicts = conflictRepository.findByStatus(enumStatus);
        return conflicts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ConflictDTO getConflictById(Long id) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + id));
        return mapToDTO(conflict);
    }

    @Transactional
    public ConflictDTO createConflict(CreateConflictDTO createDTO) {
        Conflict conflict = new Conflict();
        conflict.setName(createDTO.getName());
        conflict.setStartDate(createDTO.getStartDate());
        conflict.setDescription(createDTO.getDescription());
        conflict.setStatus(ConflictStatus.valueOf(createDTO.getStatus().toUpperCase()));

        // Gestionar la relación ManyToMany con Países
        if (createDTO.getCountryIds() != null && !createDTO.getCountryIds().isEmpty()) {
            List<Country> countries = countryRepository.findAllById(createDTO.getCountryIds());
            conflict.setCountries(new HashSet<>(countries));
        }

        Conflict savedConflict = conflictRepository.save(conflict);
        return mapToDTO(savedConflict);
    }

    @Transactional
    public ConflictDTO updateConflict(Long id, UpdateConflictDTO updateDTO) {
        Conflict conflict = conflictRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conflict not found"));

        if (updateDTO.getName() != null) conflict.setName(updateDTO.getName());
        if (updateDTO.getDescription() != null) conflict.setDescription(updateDTO.getDescription());
        if (updateDTO.getStatus() != null) {
            conflict.setStatus(ConflictStatus.valueOf(updateDTO.getStatus().toUpperCase()));
        }

        if (updateDTO.getCountryIds() != null) {
            List<Country> countries = countryRepository.findAllById(updateDTO.getCountryIds());
            conflict.setCountries(new HashSet<>(countries));
        }

        return mapToDTO(conflictRepository.save(conflict));
    }

    public void deleteConflict(Long id) {
        if (!conflictRepository.existsById(id)) {
            throw new RuntimeException("Conflict not found");
        }
        conflictRepository.deleteById(id);
    }

    // --- Helper para mapear manual (Sin MapStruct) ---
    private ConflictDTO mapToDTO(Conflict conflict) {
        ConflictDTO dto = new ConflictDTO();
        dto.setId(conflict.getId());
        dto.setName(conflict.getName());
        dto.setStatus(conflict.getStatus().name());
        dto.setStartDate(conflict.getStartDate());
        dto.setDescription(conflict.getDescription());

        // Mapear nombres de países
        List<String> countryNames = new ArrayList<>();
        for (Country country : conflict.getCountries()) {
            countryNames.add(country.getName());
        }
        dto.setCountryNames(countryNames);

        // Calcular facciones (si la lista no es nula)
        if (conflict.getFactions() != null) {
            dto.setNumberOfFactions(conflict.getFactions().size());
        } else {
            dto.setNumberOfFactions(0);
        }

        return dto;
    }
}