package com.revature.beans.controllers;

import java.util.List;
import java.util.Optional;

import com.revature.dtos.Comment;
import com.revature.dtos.FollowedId;
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

    //Gets Original Posts Only
    @Authorized
    @GetMapping(value = "/post/{userId}")
    public ResponseEntity<List<Post>> getByOriginalPost(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.postService.readByOriginalPost(userId));
    }

    //Gets posts by who you are following (main feed)
    @Authorized
    @PostMapping("/follow")
    public ResponseEntity<List<Post>> getByFollowed(@RequestBody List<FollowedId> followedIds) {
        return ResponseEntity.ok(this.postService.readByFollowed(followedIds));
    }

    @Authorized
    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<Post>> getByComments(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.readByComments(postId));
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
    @PostMapping(value = "/comment")
    public void postComment(@RequestBody Comment comment) {
        this.postService.createComment(comment);
    }

    @Authorized
    @PutMapping(value = ("/update-post/{postId}"))
    public void upsertPost(@RequestBody Post post, @PathVariable Integer postId) {
        String image = post.getImageUrl();
        String text = post.getText();
        String title = post.getTitle();
        Integer userId = post.getUser().getUserId();
    	this.postService.update(postId, image, text, title, userId);
    }

    @Authorized
    @DeleteMapping(value = "/{postId}")
    public void deletePost(@PathVariable Integer postId) {
        this.postService.deleteComment(postId);
    }
}
