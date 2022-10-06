package com.revature.beans.services;

import com.revature.beans.repositories.EventRequestRepository;
import com.revature.beans.repositories.UserEventRepository;
import com.revature.models.EventRequest;
import com.revature.models.UserEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEventService {

    private UserEventRepository repository;

    public UserEventService(UserEventRepository userEventRepository) {
        this.repository = userEventRepository;
    }

    public Optional<UserEvent> readByUserEventId(Integer userEventId) {
        return this.repository.findById(userEventId);
    }

    public List<UserEvent> readByAdmin() {
        return this.repository.findByAdmin();
    }

    public UserEvent readByCreator() {
        return this.repository.findByCreator();
    }

    public List<UserEvent> readByEventId(Integer eventId) {
        return this.repository.findByEvent_EventId(eventId);
    }

    public List<UserEvent> readByUserId(Integer userId) {
        return this.repository.findByUser_UserId(userId);
    }

    public List<UserEvent> readAll() {
        return this.repository.findAll();
    }

    public UserEvent createUserEvent(UserEvent userEvent) {
        return this.repository.save(userEvent);
    }

    public void deleteUserEvent(Integer userEventId) {
        this.repository.deleteById(userEventId);
    }
}
