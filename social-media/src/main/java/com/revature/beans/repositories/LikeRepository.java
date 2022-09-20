package com.revature.beans.repositories;

import com.revature.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findByPost_PostId(Integer postId);

    List<Like> findByUser_UserId(Integer userId);
}
