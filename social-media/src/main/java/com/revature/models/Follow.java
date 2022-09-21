package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private int followId;

    @ManyToOne
    @JoinColumn(name = "followed_id")
    private User followedUser;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User followerUser;

    public Follow() {
    }

    public Follow(int followerId, User followedUser, User followingUser) {
        this.followId = followerId;
        this.followedUser = followedUser;
        this.followerUser = followingUser;
    }

    public Follow(User followedUser, User followingUser) {
        this.followedUser = followedUser;
        this.followerUser = followingUser;
    }

    public int getFollowId() {
        return followId;
    }

    public void setFollowId(int followId) {
        this.followId = followId;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public User getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(User followerUser) {
        this.followerUser = followerUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return followId == follow.followId && Objects.equals(followedUser, follow.followedUser) && Objects.equals(followerUser, follow.followerUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, followedUser, followerUser);
    }

    @Override
    public String toString() {
        return "Follow{" +
                "followerId=" + followId +
                ", followedUser=" + followedUser +
                ", followingUser=" + followerUser +
                '}';
    }
}
