package com.revature.beans.controllers;

import com.revature.beans.services.EventInviteService;
import com.revature.models.EventInvite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/event-invites")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EventInviteController {

    private EventInviteService service;

    public EventInviteController(EventInviteService eventInviteService) {
        this.service = eventInviteService;
    }

    @GetMapping("/{eventInviteId}")
    public ResponseEntity<EventInvite> getByEventInviteId(@PathVariable Integer eventInviteId) {
        Optional<EventInvite> optionalEventInvite = this.service.readByEventInviteId(eventInviteId);
        try {
            optionalEventInvite.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalEventInvite.get());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventInvite>> getByEventId(@PathVariable Integer eventId) {
        return ResponseEntity.ok(this.service.readByEventId(eventId));
    }

    @GetMapping("/event-inviter/{userId}")
    public ResponseEntity<List<EventInvite>> getByEventInviterId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByEventInviterId(userId));
    }

    @GetMapping("/new-event-member/{userId}")
    public ResponseEntity<List<EventInvite>> getByNewEventMemberId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByNewMemberId(userId));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<EventInvite>> getByNotAccepted() {
        return ResponseEntity.ok(this.service.readByNotAccepted());
    }

    @GetMapping
    public ResponseEntity<List<EventInvite>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<EventInvite> createEventInvite(@RequestBody EventInvite eventInvite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventInvite(eventInvite));
    }

    @PutMapping
    public ResponseEntity<EventInvite> updateEventInvite(@RequestBody EventInvite eventInvite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventInvite(eventInvite));
    }

    @DeleteMapping("/{eventInviteId}")
    public void deleteEventInvite(@PathVariable Integer eventInviteId) {
        this.service.deleteEventInvite(eventInviteId);
    }
}
