package com.revature.beans.services;

import com.revature.beans.repositories.EventInviteRepository;
import com.revature.models.EventInvite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventInviteService {

    private EventInviteRepository repository;

    public EventInviteService(EventInviteRepository eventInviteRepository) {
        this.repository = eventInviteRepository;
    }

    public Optional<EventInvite> readByEventInviteId(Integer eventInviteId) {
        return this.repository.findById(eventInviteId);
    }

    public List<EventInvite> readByEventId(Integer eventId) {
        return this.repository.findByEvent_EventId(eventId);
    }

    public List<EventInvite> readByEventInviterId(Integer userId) {
        return this.repository.findByEventInviter_UserId(userId);
    }

    public List<EventInvite> readByNewMemberId(Integer userId) {
        return this.repository.findByNewEventMember_UserId(userId);
    }

    public List<EventInvite> readByNotAccepted() {
        return this.repository.findByNotAccepted();
    }

    public List<EventInvite> readAll() {
        return this.repository.findAll();
    }

    public EventInvite createEventInvite(EventInvite eventInvite) {
        return this.repository.save(eventInvite);
    }

    public void deleteEventInvite(Integer eventInviteId) {
        this.repository.deleteById(eventInviteId);
    }
}
