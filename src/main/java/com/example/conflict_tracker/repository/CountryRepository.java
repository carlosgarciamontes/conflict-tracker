package com.example.conflict_tracker.repository;

import com.example.conflict_tracker.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    // Si en el futuro necesitas buscar por código (ej: "ESP"),
    // podrías añadir: Optional<Country> findByCode(String code);
}