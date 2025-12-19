package com.example.conflict_tracker.service;

import com.example.conflict_tracker.dto.faction.*;
import com.example.conflict_tracker.model.Conflict;
import com.example.conflict_tracker.model.Country;
import com.example.conflict_tracker.model.Faction;
import com.example.conflict_tracker.repository.ConflictRepository;
import com.example.conflict_tracker.repository.CountryRepository;
import com.example.conflict_tracker.repository.FactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class FactionService {

    private final FactionRepository factionRepository;
    private final ConflictRepository conflictRepository;
    private final CountryRepository countryRepository;

    public FactionService(FactionRepository factionRepository, ConflictRepository conflictRepository, CountryRepository countryRepository) {
        this.factionRepository = factionRepository;
        this.conflictRepository = conflictRepository;
        this.countryRepository = countryRepository;
    }

    public List<FactionDTO> getAllFactions() {
        List<Faction> factions = factionRepository.findAll();
        List<FactionDTO> dtos = new ArrayList<>();
        for (Faction faction : factions) {
            dtos.add(mapToDTO(faction));
        }
        return dtos;
    }

    public List<FactionDTO> getFactionsByConflictId(Long conflictId) {
        List<Faction> factions = factionRepository.findByConflictId(conflictId);
        List<FactionDTO> dtos = new ArrayList<>();
        for (Faction faction : factions) {
            dtos.add(mapToDTO(faction));
        }
        return dtos;
    }

    public FactionDTO getFactionById(Long id) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found with id: " + id));
        return mapToDTO(faction);
    }

    @Transactional
    public FactionDTO createFaction(CreateFactionDTO createDTO) {
        Faction faction = new Faction();
        faction.setName(createDTO.getName());

        // 1. Vincular con el Conflicto (Obligatorio)
        Conflict conflict = conflictRepository.findById(createDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createDTO.getConflictId()));
        faction.setConflict(conflict);

        // 2. Vincular con Países seguidores (Opcional)
        if (createDTO.getSupporterCountryIds() != null && !createDTO.getSupporterCountryIds().isEmpty()) {
            List<Country> countries = countryRepository.findAllById(createDTO.getSupporterCountryIds());
            faction.setSupporters(new HashSet<>(countries));
        }

        Faction savedFaction = factionRepository.save(faction);
        return mapToDTO(savedFaction);
    }

    @Transactional
    public FactionDTO updateFaction(Long id, UpdateFactionDTO updateDTO) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faction not found"));

        if (updateDTO.getName() != null) {
            faction.setName(updateDTO.getName());
        }

        // Actualizar relaciones con países si se envía la lista
        if (updateDTO.getSupporterCountryIds() != null) {
            List<Country> countries = countryRepository.findAllById(updateDTO.getSupporterCountryIds());
            faction.setSupporters(new HashSet<>(countries));
        }

        return mapToDTO(factionRepository.save(faction));
    }

    public void deleteFaction(Long id) {
        if (!factionRepository.existsById(id)) {
            throw new RuntimeException("Faction not found");
        }
        factionRepository.deleteById(id);
    }

    // --- Mapper Manual ---
    private FactionDTO mapToDTO(Faction faction) {
        FactionDTO dto = new FactionDTO();
        dto.setId(faction.getId());
        dto.setName(faction.getName());

        // Mapear nombre del conflicto para mostrarlo en el JSON
        if (faction.getConflict() != null) {
            dto.setConflictName(faction.getConflict().getName());
        }

        // Mapear nombres de los países seguidores
        List<String> countryNames = new ArrayList<>();
        if (faction.getSupporters() != null) {
            for (Country c : faction.getSupporters()) {
                countryNames.add(c.getName());
            }
        }
        dto.setSupporterCountryNames(countryNames);

        return dto;
    }
}