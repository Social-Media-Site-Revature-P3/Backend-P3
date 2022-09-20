package com.revature.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByUser_UserId(Integer userId);
}
