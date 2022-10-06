package com.revature.beans.services;

import com.revature.beans.repositories.EventRequestRepository;
import com.revature.models.EventRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventRequestService {

    private EventRequestRepository repository;

    public EventRequestService(EventRequestRepository eventRequestRepository) {
        this.repository = eventRequestRepository;
    }

    public Optional<EventRequest> readByEventRequestId(Integer eventRequestId) {
        return this.repository.findById(eventRequestId);
    }

    public List<EventRequest> readByEventId(Integer eventId) {
        return this.repository.findByEvent_EventId(eventId);
    }

    public List<EventRequest> readByUserId(Integer userId) {
        return this.repository.findByUser_UserId(userId);
    }

    public List<EventRequest> readByNotAccepted() {
        return this.repository.findByNotAccepted();
    }

    public List<EventRequest> readAll() {
        return this.repository.findAll();
    }

    public EventRequest createEventRequest(EventRequest eventRequest) {
        return this.repository.save(eventRequest);
    }

    public void deleteEventRequest(Integer eventRequestId) {
        this.repository.deleteById(eventRequestId);
    }
}
