package com.revature.beans.services;

import com.revature.beans.repositories.BookmarkRepository;
import com.revature.models.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {
    private BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Optional<Bookmark> readByBookmarkId(Integer bookmarkId) {
        return this.bookmarkRepository.findById(bookmarkId);
    }

    public List<Bookmark> readByPostId(Integer postId) {
        return this.bookmarkRepository.findByPost_PostId(postId);
    }

    public List<Bookmark> readByUserId(Integer userId) {
        return this.bookmarkRepository.findByUser_UserId(userId);
    }

    public List<Bookmark> readAll() {
        return this.bookmarkRepository.findAll();
    }

    public Bookmark createBookmark(Bookmark bookmark) {
        return this.bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(Integer bookmarkId) {
        this.bookmarkRepository.deleteById(bookmarkId);
    }
}
