package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private int bookmarkId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Bookmark() {
    }

    public Bookmark(int bookmarkId, Post post, User user) {
        this.bookmarkId = bookmarkId;
        this.post = post;
        this.user = user;
    }

    public Bookmark(Post post, User user) {
        this.post = post;
        this.user = user;
    }


    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookmark bookmark = (Bookmark) o;
        return bookmarkId == bookmark.bookmarkId && Objects.equals(post, bookmark.post) && Objects.equals(user, bookmark.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookmarkId, post, user);
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "bookmarkId=" + bookmarkId +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
