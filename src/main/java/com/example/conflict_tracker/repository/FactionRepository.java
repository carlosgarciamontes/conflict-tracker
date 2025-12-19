package com.example.conflict_tracker.repository;

import com.example.conflict_tracker.model.Faction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FactionRepository extends JpaRepository<Faction, Long> {

    // Encuentra todas las facciones asociadas a un ID de conflicto
    List<Faction> findByConflictId(Long conflictId);
}