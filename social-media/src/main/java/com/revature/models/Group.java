package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name= "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int groupId;
    private String name;
    private String about;
    @Column(name = "invite_only")
    private boolean inviteOnly;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserGroup> groups;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GroupRequest> groupRequests;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Event> events;

    public Group() {
    }

    public Group(int groupId, String name, String about, boolean inviteOnly, List<UserGroup> groups, List<Post> posts, List<GroupRequest> groupRequests, List<Event> events) {
        this.groupId = groupId;
        this.name = name;
        this.about = about;
        this.inviteOnly = inviteOnly;
        this.groups = groups;
        this.posts = posts;
        this.groupRequests = groupRequests;
        this.events = events;
    }

    public Group(String name, String about, boolean inviteOnly, List<UserGroup> groups, List<Post> posts, List<GroupRequest> groupRequests, List<Event> events) {
        this.name = name;
        this.about = about;
        this.inviteOnly = inviteOnly;
        this.groups = groups;
        this.posts = posts;
        this.groupRequests = groupRequests;
        this.events = events;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isInviteOnly() {
        return inviteOnly;
    }

    public void setInviteOnly(boolean inviteOnly) {
        this.inviteOnly = inviteOnly;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<GroupRequest> getGroupRequests() {
        return groupRequests;
    }

    public void setGroupRequests(List<GroupRequest> groupRequests) {
        this.groupRequests = groupRequests;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId && inviteOnly == group.inviteOnly && Objects.equals(name, group.name) && Objects.equals(about, group.about) && Objects.equals(groups, group.groups) && Objects.equals(posts, group.posts) && Objects.equals(groupRequests, group.groupRequests) && Objects.equals(events, group.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name, about, inviteOnly, groups, posts, groupRequests, events);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", inviteOnly=" + inviteOnly +
                ", groups=" + groups +
                ", posts=" + posts +
                ", groupRequests=" + groupRequests +
                ", events=" + events +
                '}';
    }
}
