package com.revature.beans.repositories;

import com.revature.models.GroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupInviteRepository extends JpaRepository<GroupInvite, Integer> {

    List<GroupInvite> findByGroup_GroupId(Integer eventId);

    List<GroupInvite> findByGroupInviter_UserId(Integer userId);

    List<GroupInvite> findByNewGroupMember_UserId(Integer userId);

    @Query(value = "SELECT * FROM group_invites WHERE accepted = false", nativeQuery = true)
    List<GroupInvite> findByNotAccepted();
}
