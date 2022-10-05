package com.revature.beans.repositories;

import com.revature.models.GroupRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRequestRepository extends JpaRepository<GroupRequest, Integer> {

    List<GroupRequest> findByGroup_GroupId(Integer groupId);

    List<GroupRequest> findByUser_UserId(Integer userId);

    @Query(value = "SELECT * FROM group_requests WHERE accepted = false", nativeQuery = true)
    List<GroupRequest> findByNotAccepted();
}
