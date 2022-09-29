package com.revature.beans.controllers;

import com.revature.annotations.Authorized;
import com.revature.beans.services.FollowService;
import com.revature.models.Follow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/follows")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class FollowController {

    private FollowService service;

    public FollowController(FollowService followService) {
        this.service = followService;
    }

    //Gets a follow by Id (most likely useless)
    //@Authorized
    @GetMapping("/{followId}")
    public ResponseEntity<Follow> getByFollowId(@PathVariable Integer followId) {
        Optional<Follow> optionalFollow = this.service.readByFollowId(followId);
        try {
            optionalFollow.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalFollow.get());
    }

    //Gets a list of users that are following a specific person
    //@Authorized
    @GetMapping("/followed/{followedId}")
    public ResponseEntity<List<Follow>> getByFollowedId(@PathVariable Integer followedId) {
        return ResponseEntity.ok(this.service.readByFollowedId(followedId));
    }

    //Gets a list of users that are being followed by a specific user
    //@Authorized
    @GetMapping("/follower/{followerId}")
    public ResponseEntity<List<Follow>> getByFollowerId(@PathVariable Integer followerId) {
        return ResponseEntity.ok(this.service.readByFollowerId(followerId));
    }

    //Gets a List of all follows (probably useless for our purposes)
    //@Authorized
    @GetMapping
    public ResponseEntity<List<Follow>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    //@Authorized
    @PostMapping
    public ResponseEntity<Follow> createFollow(@RequestBody Follow follow) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createFollow(follow));
    }

    //@Authorized
    @DeleteMapping("/{followId}")
    public void deleteFollow(@PathVariable Integer followId) {
        this.service.deleteFollow(followId);
    }
}
