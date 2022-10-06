package com.revature.beans.repositories;

import com.revature.models.EventInvite;
import com.revature.models.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventInviteRepository extends JpaRepository<EventInvite, Integer> {

    List<EventInvite> findByEvent_EventId(Integer eventId);

    List<EventInvite> findByEventInviter_UserId(Integer userId);

    List<EventInvite> findByNewEventMember_UserId(Integer userId);

    @Query(value = "SELECT * FROM event_invites WHERE accepted = false", nativeQuery = true)
    List<EventInvite> findByNotAccepted();
}
