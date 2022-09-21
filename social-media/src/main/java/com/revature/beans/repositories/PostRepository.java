package com.revature.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByUser_UserId(Integer userId);

    @Query(value = "SELECT * FROM posts p LEFT JOIN posts_comments pc ON p.post_id = pc.comments_post_id WHERE pc.post_post_id = :postId", nativeQuery = true)
    List<Post> findCommentsByPost(@Param("postId") Integer postId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE posts SET image_url = :image, text = :text, title = :title, user_id = :userId WHERE post_id = :postId", nativeQuery = true)
    void updatePostById(@Param("postId") Integer postId, @Param("image") String image, @Param("text") String text, @Param("title") String title, @Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO posts_comments (comments_post_id, post_post_id) VALUES (:commentId, :postId);", nativeQuery = true)
    void saveComment(@Param("commentId") Integer commentId, @Param("postId") Integer postId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM posts_comments WHERE post_post_id = :postId OR comments_post_id = :postId", nativeQuery = true)
    void deleteComment(@Param("postId") Integer postId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM posts WHERE post_id = :postId", nativeQuery = true)
    void deletePost(@Param("postId") Integer postId);

}
