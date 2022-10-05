package com.revature.beans.repositories;

import com.revature.models.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {

    @Query(value = "SELECT * FROM user_events WHERE admin = true", nativeQuery = true)
    List<UserEvent> findByAdmin();

    @Query(value = "SELECT * FROM user_events WHERE creator = true", nativeQuery = true)
    UserEvent findByCreator();

    List<UserEvent> findByEvent_EventId(Integer eventId);

    List<UserEvent> findByUser_UserId(Integer userId);
}
