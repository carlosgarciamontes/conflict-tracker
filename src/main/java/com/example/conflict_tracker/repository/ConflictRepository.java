package com.example.conflict_tracker.repository;

import com.example.conflict_tracker.model.Conflict;
import com.example.conflict_tracker.model.enums.ConflictStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConflictRepository extends JpaRepository<Conflict, Long> {
    List<Conflict> findByStatus(ConflictStatus status);
    List<Conflict> findByCountries_Code(String code);
}