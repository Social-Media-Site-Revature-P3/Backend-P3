package com.revature.beans.repositories;

import com.revature.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    List<Follow> findByFollowedUser_UserId(Integer userId);

    List<Follow> findByFollowerUser_UserId(Integer userId);

    @Transactional
    @Query(value = "DELETE FROM follows WHERE followed_id = :followedId AND follower_id = :followerId", nativeQuery = true)
    void deleteFollowByFollowedIdAndFollowerId(@Param("followedId") Integer followedId, @Param("followerId") Integer followerId);
}
