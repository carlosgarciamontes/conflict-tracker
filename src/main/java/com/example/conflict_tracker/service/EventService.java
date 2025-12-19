package com.example.conflict_tracker.service;

import com.example.conflict_tracker.dto.event.*;
import com.example.conflict_tracker.model.Conflict;
import com.example.conflict_tracker.model.Event;
import com.example.conflict_tracker.repository.ConflictRepository;
import com.example.conflict_tracker.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ConflictRepository conflictRepository;

    public EventService(EventRepository eventRepository, ConflictRepository conflictRepository) {
        this.eventRepository = eventRepository;
        this.conflictRepository = conflictRepository;
    }

    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> dtos = new ArrayList<>();
        for (Event event : events) {
            dtos.add(mapToDTO(event));
        }
        return dtos;
    }

    public List<EventDTO> getEventsByConflictId(Long conflictId) {
        List<Event> events = eventRepository.findByConflictId(conflictId);
        List<EventDTO> dtos = new ArrayList<>();
        for (Event event : events) {
            dtos.add(mapToDTO(event));
        }
        return dtos;
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return mapToDTO(event);
    }

    @Transactional
    public EventDTO createEvent(CreateEventDTO createDTO) {
        Event event = new Event();
        event.setEventDate(createDTO.getEventDate());
        event.setLocation(createDTO.getLocation());
        event.setDescription(createDTO.getDescription());

        // Vincular con el Conflicto
        Conflict conflict = conflictRepository.findById(createDTO.getConflictId())
                .orElseThrow(() -> new RuntimeException("Conflict not found with id: " + createDTO.getConflictId()));
        event.setConflict(conflict);

        Event savedEvent = eventRepository.save(event);
        return mapToDTO(savedEvent);
    }

    @Transactional
    public EventDTO updateEvent(Long id, UpdateEventDTO updateDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (updateDTO.getEventDate() != null) event.setEventDate(updateDTO.getEventDate());
        if (updateDTO.getLocation() != null) event.setLocation(updateDTO.getLocation());
        if (updateDTO.getDescription() != null) event.setDescription(updateDTO.getDescription());

        return mapToDTO(eventRepository.save(event));
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found");
        }
        eventRepository.deleteById(id);
    }

    // --- Mapper Manual ---
    private EventDTO mapToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setEventDate(event.getEventDate());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());

        if (event.getConflict() != null) {
            dto.setConflictName(event.getConflict().getName());
        }

        return dto;
    }
}