package com.revature.beans.controllers;

import com.revature.beans.services.EventRequestService;
import com.revature.beans.services.UserEventService;
import com.revature.models.EventRequest;
import com.revature.models.UserEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user-events")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserEventController {

    private UserEventService service;

    public UserEventController(UserEventService userEventService) {
        this.service = userEventService;
    }

    @GetMapping("/{userEventId}")
    public ResponseEntity<UserEvent> getByUserEventId(@PathVariable Integer userEventId) {
        Optional<UserEvent> optionalUserEvent = this.service.readByUserEventId(userEventId);
        try {
            optionalUserEvent.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalUserEvent.get());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserEvent>> getByAmin() {
        return ResponseEntity.ok(this.service.readByAdmin());
    }

    @GetMapping("/creator")
    public ResponseEntity<UserEvent> getByCreator() {
        return ResponseEntity.ok(this.service.readByCreator());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<UserEvent>> getByEventId(@PathVariable Integer eventId) {
        return ResponseEntity.ok(this.service.readByEventId(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserEvent>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserEvent>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<UserEvent> createUserEvent(@RequestBody UserEvent userEvent) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createUserEvent(userEvent));
    }

    @PutMapping
    public ResponseEntity<UserEvent> updateUserEvent(@RequestBody UserEvent userEvent) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createUserEvent(userEvent));
    }

    @DeleteMapping("/{userEventId}")
    public void deleteUserEvent(@PathVariable Integer userEventId) {
        this.service.deleteUserEvent(userEventId);
    }
}
