package com.revature.beans.controllers;

import com.revature.annotations.Authorized;
import com.revature.beans.services.BookmarkService;
import com.revature.models.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookmarks")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class BookmarkController {

    private BookmarkService service;

    public BookmarkController(BookmarkService bookmarkService) {
        this.service = bookmarkService;
    }

    //@Authorized
    @GetMapping(value = "/{bookmarkId}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Integer bookmarkId) {
        Optional<Bookmark> optionalBookmark = this.service.readByBookmarkId(bookmarkId);
        try {
            optionalBookmark.isPresent();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalBookmark.get());
    }

    //Gets all bookmarks related to a post
    //@Authorized
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Bookmark>> getByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.service.readByPostId(postId));
    }

    //Gets all bookmarks from a user
    //@Authorized
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bookmark>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    //Gets all bookmarks (probably useless)
    //@Authorized
    @GetMapping
    public ResponseEntity<List<Bookmark>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    //No Update parameter. Bookmark either exists, or doesn't
    //@Authorized
    @PostMapping
    public ResponseEntity<Bookmark> createLike(@RequestBody Bookmark bookmark) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createBookmark(bookmark));
    }

    //@Authorized
    @DeleteMapping("/{bookmarkId}")
    public void deleteLike(@PathVariable Integer bookmarkId) {
        this.service.deleteBookmark(bookmarkId);
    }
}
