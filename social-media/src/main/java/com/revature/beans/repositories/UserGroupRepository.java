package com.revature.beans.repositories;

import com.revature.models.UserEvent;
import com.revature.models.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

    @Query(value = "SELECT * FROM user_groups WHERE admin = true", nativeQuery = true)
    List<UserGroup> findByAdmin();

    @Query(value = "SELECT * FROM user_groups WHERE creator = true", nativeQuery = true)
    UserGroup findByCreator();

    List<UserGroup> findByGroup_GroupId(Integer group);

    List<UserGroup> findByUser_UserId(Integer userId);
}
