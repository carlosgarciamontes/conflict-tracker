package com.example.conflict_tracker.controller;

import com.example.conflict_tracker.dto.event.CreateEventDTO;
import com.example.conflict_tracker.dto.event.EventDTO;
import com.example.conflict_tracker.dto.event.UpdateEventDTO;
import com.example.conflict_tracker.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // GET /api/v1/events
    // GET /api/v1/events?conflictId=1
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents(@RequestParam(required = false) Long conflictId) {
        if (conflictId != null) {
            return ResponseEntity.ok(eventService.getEventsByConflictId(conflictId));
        }
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // GET /api/v1/events/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // POST /api/v1/events
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody CreateEventDTO createDTO) {
        EventDTO createdEvent = eventService.createEvent(createDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // PUT /api/v1/events/{id}
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody UpdateEventDTO updateDTO) {
        return ResponseEntity.ok(eventService.updateEvent(id, updateDTO));
    }

    // DELETE /api/v1/events/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}