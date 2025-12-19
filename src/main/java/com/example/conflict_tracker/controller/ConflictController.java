package com.example.conflict_tracker.controller;

import com.example.conflict_tracker.dto.conflict.ConflictDTO;
import com.example.conflict_tracker.dto.conflict.CreateConflictDTO;
import com.example.conflict_tracker.dto.conflict.UpdateConflictDTO;
import com.example.conflict_tracker.service.ConflictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
@CrossOrigin(origins = "*") // Permite peticiones desde el frontend local
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping
    public ResponseEntity<List<ConflictDTO>> getAllConflicts(@RequestParam(required = false) String status) {
        if (status != null) {
            return ResponseEntity.ok(conflictService.getConflictsByStatus(status));
        }
        return ResponseEntity.ok(conflictService.getAllConflicts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConflictDTO> getConflictById(@PathVariable Long id) {
        return ResponseEntity.ok(conflictService.getConflictById(id));
    }

    @PostMapping
    public ResponseEntity<ConflictDTO> createConflict(@RequestBody CreateConflictDTO createDTO) {
        ConflictDTO created = conflictService.createConflict(createDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConflictDTO> updateConflict(@PathVariable Long id, @RequestBody UpdateConflictDTO updateDTO) {
        return ResponseEntity.ok(conflictService.updateConflict(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConflict(@PathVariable Long id) {
        conflictService.deleteConflict(id);
        return ResponseEntity.noContent().build();
    }
}