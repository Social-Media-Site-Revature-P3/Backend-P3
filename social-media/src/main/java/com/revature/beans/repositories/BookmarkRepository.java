package com.revature.beans.repositories;

import com.revature.models.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

    List<Bookmark> findByPost_PostId(Integer postId);

    List<Bookmark> findByUser_UserId(Integer userId);
}
