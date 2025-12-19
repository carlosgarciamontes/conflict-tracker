package com.example.conflict_tracker.repository;

import com.example.conflict_tracker.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    // Encuentra todos los eventos asociados a un ID de conflicto
    List<Event> findByConflictId(Long conflictId);
}