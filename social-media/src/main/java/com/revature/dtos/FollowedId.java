package com.revature.dtos;

import java.util.Objects;

public class FollowedId {
    private int followedId;

    public FollowedId() {
    }

    public FollowedId(int followedId) {
        this.followedId = followedId;
    }

    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowedId that = (FollowedId) o;
        return followedId == that.followedId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(followedId);
    }

    @Override
    public String toString() {
        return "FollowedId{" +
                "followedId=" + followedId +
                '}';
    }
}
