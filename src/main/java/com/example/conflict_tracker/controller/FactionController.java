package com.example.conflict_tracker.controller;

import com.example.conflict_tracker.dto.faction.CreateFactionDTO;
import com.example.conflict_tracker.dto.faction.FactionDTO;
import com.example.conflict_tracker.dto.faction.UpdateFactionDTO;
import com.example.conflict_tracker.service.FactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para pruebas)
public class FactionController {

    private final FactionService factionService;

    // Inyección de dependencia por constructor
    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    // GET /api/v1/factions
    // GET /api/v1/factions?conflictId=1
    @GetMapping
    public ResponseEntity<List<FactionDTO>> getAllFactions(@RequestParam(required = false) Long conflictId) {
        if (conflictId != null) {
            return ResponseEntity.ok(factionService.getFactionsByConflictId(conflictId));
        }
        return ResponseEntity.ok(factionService.getAllFactions());
    }

    // GET /api/v1/factions/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getFactionById(@PathVariable Long id) {
        return ResponseEntity.ok(factionService.getFactionById(id));
    }

    // POST /api/v1/factions
    @PostMapping
    public ResponseEntity<FactionDTO> createFaction(@RequestBody CreateFactionDTO createDTO) {
        FactionDTO createdFaction = factionService.createFaction(createDTO);
        return new ResponseEntity<>(createdFaction, HttpStatus.CREATED);
    }

    // PUT /api/v1/factions/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> updateFaction(@PathVariable Long id, @RequestBody UpdateFactionDTO updateDTO) {
        return ResponseEntity.ok(factionService.updateFaction(id, updateDTO));
    }

    // DELETE /api/v1/factions/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaction(@PathVariable Long id) {
        factionService.deleteFaction(id);
        return ResponseEntity.noContent().build();
    }
}