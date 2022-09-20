package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId;
    private boolean liked;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like() {
    }

    public Like(int likeId, boolean liked, Post post, User user) {
        this.likeId = likeId;
        this.liked = liked;
        this.post = post;
        this.user = user;
    }

    public Like(boolean liked, Post post, User user) {
        this.liked = liked;
        this.post = post;
        this.user = user;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
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
        Like like = (Like) o;
        return likeId == like.likeId && liked == like.liked && Objects.equals(post, like.post) && Objects.equals(user, like.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likeId, liked, post, user);
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", liked=" + liked +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
