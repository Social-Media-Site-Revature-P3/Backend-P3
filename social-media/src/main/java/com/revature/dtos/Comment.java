package com.revature.dtos;

import java.util.Objects;

public class Comment {

    private int commentId;
    private int postId;

    public Comment() {
    }

    public Comment(int commentId, int postId) {
        this.commentId = commentId;
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId && postId == comment.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, postId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                '}';
    }
}
