package com.revature.beans.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.revature.annotations.Authorized;
import com.revature.models.Post;
import com.revature.beans.services.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PostController {

	private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //gets a single post by its ID (view single post)
    @Authorized
    @GetMapping(value = "/{postId}")
    public ResponseEntity<Post> getByPostId(@PathVariable Integer postId) {
        Optional<Post> optionalPost = this.postService.readByPostId(postId);
        try {
            optionalPost.isPresent();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalPost.get());
    }

    //Gets all Posts by a user (List of posts on profile page)
    @Authorized
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Post>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.postService.readByUserId(userId));
    }

    //Gets posts by who you are following (main feed)
    @Authorized
    @GetMapping("/follow")
    public ResponseEntity<List<Post>> getByFollowed(@RequestBody List<Integer> followedIds) {
        return ResponseEntity.ok(this.postService.readByFollowed(followedIds));
    }

    //Gets all posts (not particularly useful(?))
    @Authorized
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
    	return ResponseEntity.ok(this.postService.readAll());
    }

    @Authorized
    @PostMapping
    public ResponseEntity<Post> postPost(@RequestBody Post post) {
        return ResponseEntity.ok(this.postService.upsert(post));
    }

    @Authorized
    @PutMapping
    public ResponseEntity<Post> upsertPost(@RequestBody Post post) {
    	return ResponseEntity.ok(this.postService.upsert(post));
    }

    @Authorized
    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
    }
}
