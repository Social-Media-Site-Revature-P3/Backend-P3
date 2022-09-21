package com.revature.beans.services;

import com.revature.beans.repositories.FollowRepository;
import com.revature.models.Follow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {

    private FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    public Optional<Follow> readByFollowId(Integer followId) {
        return this.followRepository.findById(followId);
    }

    public List<Follow> readByFollowedId(Integer followedId) {
        System.out.println(this.followRepository.findByFollowedUser_UserId(followedId));
        return this.followRepository.findByFollowedUser_UserId(followedId);
    }

    public List<Follow> readByFollowerId(Integer followerId) {
        return this.followRepository.findByFollowerUser_UserId(followerId);
    }

    public List<Follow> readAll() {
        return this.followRepository.findAll();
    }

    public Follow createFollow(Follow follow) {
        return this.followRepository.save(follow);
    }

    public void deleteFollow(Integer followId) {
        this.followRepository.deleteById(followId);
    }
}
