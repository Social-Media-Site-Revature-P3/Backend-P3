package com.revature.beans.repositories;

import com.revature.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    List<Follow> findByFollowedUser_UserId(Integer userId);

    List<Follow> findByFollowerUser_UserId(Integer userId);
}
