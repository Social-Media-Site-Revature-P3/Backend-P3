package com.revature.beans.repositories;

import com.revature.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

    List<Like> findByPost_PostId(Integer postId);

    @Query(value = "SELECT * FROM likes WHERE post_id = :postId AND liked = true", nativeQuery = true)
    List<Like> findByLiked(Integer postId);

    @Query(value = "SELECT * FROM likes WHERE post_id = :postId AND liked = false", nativeQuery = true)
    List<Like> findByDisliked(Integer postId);

    List<Like> findByUser_UserId(Integer userId);
}
