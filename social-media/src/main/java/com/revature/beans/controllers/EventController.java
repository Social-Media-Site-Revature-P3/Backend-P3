package com.revature.beans.controllers;

import com.revature.beans.services.EventService;
import com.revature.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EventController {

    private EventService service;

    public EventController(EventService eventService) {
        this.service = eventService;
    }

    @GetMapping(value = "/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer eventId) {
        Optional<Event> optionalEvent = this.service.readByEventId(eventId);
        try {
            optionalEvent.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalEvent.get());
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Event>> getEventByName(@PathVariable String name) {
        return ResponseEntity.ok(this.service.readByEventName(name));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEvent(event));
    }

    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEvent(event));
    }

    @DeleteMapping(value = "/{eventId}")
    public void deleteEvent(@PathVariable Integer eventId) {
        this.service.deleteEvent(eventId);
    }
}
