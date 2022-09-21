package com.revature.beans.services;

import com.revature.beans.repositories.LikeRepository;
import com.revature.models.Like;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private LikeRepository likeRepo;

    public LikeService(LikeRepository likeRepo) {
        this.likeRepo = likeRepo;
    }

    public Optional<Like> readByLikeId(Integer likeId) {
        return this.likeRepo.findById(likeId);
    }

    public List<Like> readByPostId(Integer postId) {
        return this.likeRepo.findByPost_PostId(postId);
    }

    public List<Like> readByUserId(Integer userId) {
        return this.likeRepo.findByUser_UserId(userId);
    }

    public List<Like> readAll() {
        return this.likeRepo.findAll();
    }

    public Like createLike(Like like) {
        return this.likeRepo.save(like);
    }

    public void deleteLike(Integer likeId) {
        this.likeRepo.deleteById(likeId);
    }
}
