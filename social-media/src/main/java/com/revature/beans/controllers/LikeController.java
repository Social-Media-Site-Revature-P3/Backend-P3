package com.revature.beans.controllers;

import com.revature.annotations.Authorized;
import com.revature.beans.services.LikeService;
import com.revature.models.Like;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LikeController {

    private LikeService service;

    public LikeController(LikeService likeService) {
        this.service = likeService;
    }

    //Gets an individual like (useless for us)
    @Authorized
    @GetMapping(value = "/{likeId}")
    public ResponseEntity<Like> getLikeById(@PathVariable Integer likeId) {
        Optional<Like> optionalLike = this.service.readByLikeId(likeId);
        try {
            optionalLike.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalLike.get());
    }

    //Gets all likes related to a post

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<List<Like>>> getByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.service.readByPostId(postId));
    }

    //Gets all likes from a user (possibly useless(?), depends on if we want to display all liked posts by a user)
    @Authorized
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Like>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    //Gets all likes (probably useless)
    @Authorized
    @GetMapping
    public ResponseEntity<List<Like>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    //No Update parameter. Workflow on front end to compensate
    @Authorized
    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createLike(like));
    }

    @Authorized
    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Integer likeId) {
        this.service.deleteLike(likeId);
    }

}
