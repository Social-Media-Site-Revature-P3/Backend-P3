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
    private String picture;
    private String name;
    private String about;
    @Column(name = "invite_only")
    private boolean inviteOnly;
    private boolean request;

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

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<GroupInvite> groupInvites;

    public Group() {
    }

    public Group(int groupId, String picture, String name, String about, boolean inviteOnly, boolean request, List<UserGroup> groups, List<Post> posts, List<GroupRequest> groupRequests, List<Event> events, List<GroupInvite> groupInvites) {
        this.groupId = groupId;
        this.picture = picture;
        this.name = name;
        this.about = about;
        this.inviteOnly = inviteOnly;
        this.request = request;
        this.groups = groups;
        this.posts = posts;
        this.groupRequests = groupRequests;
        this.events = events;
        this.groupInvites = groupInvites;
    }

    public Group(String picture, String name, String about, boolean inviteOnly, boolean request, List<UserGroup> groups, List<Post> posts, List<GroupRequest> groupRequests, List<Event> events, List<GroupInvite> groupInvites) {
        this.picture = picture;
        this.name = name;
        this.about = about;
        this.inviteOnly = inviteOnly;
        this.request = request;
        this.groups = groups;
        this.posts = posts;
        this.groupRequests = groupRequests;
        this.events = events;
        this.groupInvites = groupInvites;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public List<GroupInvite> getGroupInvites() {
        return groupInvites;
    }

    public void setGroupInvites(List<GroupInvite> groupInvites) {
        this.groupInvites = groupInvites;
    }

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId && inviteOnly == group.inviteOnly && request == group.request && Objects.equals(picture, group.picture) && Objects.equals(name, group.name) && Objects.equals(about, group.about) && Objects.equals(groups, group.groups) && Objects.equals(posts, group.posts) && Objects.equals(groupRequests, group.groupRequests) && Objects.equals(events, group.events) && Objects.equals(groupInvites, group.groupInvites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, picture, name, about, inviteOnly, request, groups, posts, groupRequests, events, groupInvites);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", inviteOnly=" + inviteOnly +
                ", request=" + request +
                ", groups=" + groups +
                ", posts=" + posts +
                ", groupRequests=" + groupRequests +
                ", events=" + events +
                ", groupInvites=" + groupInvites +
                '}';
    }
}