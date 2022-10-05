package com.revature.beans.repositories;

import com.revature.models.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Integer> {

    List<EventRequest> findByEvent_EventId(Integer eventId);

    List<EventRequest> findByUser_UserId(Integer userId);

    @Query(value = "SELECT * FROM event_requests WHERE accepted = false", nativeQuery = true)
    List<EventRequest> findByNotAccepted();
}
