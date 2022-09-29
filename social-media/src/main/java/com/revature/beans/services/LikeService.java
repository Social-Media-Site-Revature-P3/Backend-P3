package com.revature.beans.services;

import com.revature.beans.repositories.LikeRepository;
import com.revature.models.Like;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<List<Like>> readByPostId(Integer postId) {
        List<List<Like>> post = new ArrayList<>();
        List<Like> liked = this.likeRepo.findByLiked(postId);
        List<Like> disliked = this.likeRepo.findByDisliked(postId);
        post.add(liked);
        post.add(disliked);
        return post;
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
