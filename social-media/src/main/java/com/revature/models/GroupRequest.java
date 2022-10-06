package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "group_requests")
public class GroupRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_request_id")
    private int groupRequestId;
    private String text;
    private boolean accepted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public GroupRequest() {
    }

    public GroupRequest(int groupRequestId, String text, boolean accepted, User user, Group group) {
        this.groupRequestId = groupRequestId;
        this.text = text;
        this.accepted = accepted;
        this.user = user;
        this.group = group;
    }

    public GroupRequest(String text, boolean accepted, User user, Group group) {
        this.text = text;
        this.accepted = accepted;
        this.user = user;
        this.group = group;
    }

    public int getGroupRequestId() {
        return groupRequestId;
    }

    public void setGroupRequestId(int groupRequestId) {
        this.groupRequestId = groupRequestId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRequest that = (GroupRequest) o;
        return groupRequestId == that.groupRequestId && accepted == that.accepted && Objects.equals(text, that.text) && Objects.equals(user, that.user) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupRequestId, text, accepted, user, group);
    }

    @Override
    public String toString() {
        return "GroupRequest{" +
                "groupRequestId=" + groupRequestId +
                ", text='" + text + '\'' +
                ", accepted=" + accepted +
                ", user=" + user +
                ", group=" + group +
                '}';
    }
}
