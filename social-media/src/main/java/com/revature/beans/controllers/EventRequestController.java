package com.revature.beans.controllers;

import com.revature.beans.services.EventRequestService;
import com.revature.models.EventRequest;
import com.revature.models.Follow;
import com.revature.models.GroupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/event-requests")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EventRequestController {

    private EventRequestService service;

    public EventRequestController(EventRequestService eventRequestService) {
        this.service = eventRequestService;
    }

    @GetMapping("/{eventRequestId}")
    public ResponseEntity<EventRequest> getByEventRequestId(@PathVariable Integer eventRequestId) {
        Optional<EventRequest> optionalEventRequest = this.service.readByEventRequestId(eventRequestId);
        try {
            optionalEventRequest.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalEventRequest.get());
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventRequest>> getByEventId(@PathVariable Integer eventId) {
        return ResponseEntity.ok(this.service.readByEventId(eventId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventRequest>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<EventRequest>> getByNotAccepted() {
        return ResponseEntity.ok(this.service.readByNotAccepted());
    }

    @GetMapping
    public ResponseEntity<List<EventRequest>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<EventRequest> createEventRequest(@RequestBody EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventRequest(eventRequest));
    }

    @PutMapping
    public ResponseEntity<EventRequest> updateEventRequest(@RequestBody EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventRequest(eventRequest));
    }

    @DeleteMapping("/{eventRequestId}")
    public void deleteEventRequest(@PathVariable Integer eventRequestId) {
        this.service.deleteEventRequest(eventRequestId);
    }
}
