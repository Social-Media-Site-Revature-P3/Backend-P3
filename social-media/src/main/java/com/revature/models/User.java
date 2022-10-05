package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String nickname;
    private String password;
    private String aboutMe;
    private String firstName;
    private String lastName;

    public User(int userId, String email, String nickname, String password, String aboutMe, String firstName, String lastName, String profilePicture) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    private String profilePicture;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<SecurityQuestion> securityQuestions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "followerUser")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Follow>  following;

    @OneToMany(mappedBy = "followedUser")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Follow> followed;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserEvent> events;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<EventRequest> eventRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserGroup> groups;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<GroupRequest> groupRequests;

    @OneToMany(mappedBy = "eventInviter")
    @JsonIgnore
    private List<EventInvite> eventInvites;

    @OneToMany(mappedBy = "newEventMember")
    @JsonIgnore
    private List<EventInvite> newEventMembers;

    @OneToMany(mappedBy = "groupInviter")
    @JsonIgnore
    private List<GroupInvite> groupInvites;

    @OneToMany(mappedBy = "newGroupMember")
    @JsonIgnore
    private List<GroupInvite> newGroupMembers;

    public User() {
    }

    public User(int userId, String email, String nickname, String password, String aboutMe, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks, List<UserEvent> events, List<EventRequest> eventRequests, List<UserGroup> groups, List<GroupRequest> groupRequests, List<EventInvite> eventInvites, List<EventInvite> newEventMembers, List<GroupInvite> groupInvites, List<GroupInvite> newGroupMembers) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
        this.events = events;
        this.eventRequests = eventRequests;
        this.groups = groups;
        this.groupRequests = groupRequests;
        this.eventInvites = eventInvites;
        this.newEventMembers = newEventMembers;
        this.groupInvites = groupInvites;
        this.newGroupMembers = newGroupMembers;
    }

    public User(String email, String nickname, String password, String aboutMe, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks, List<UserEvent> events, List<EventRequest> eventRequests, List<UserGroup> groups, List<GroupRequest> groupRequests, List<EventInvite> eventInvites, List<EventInvite> newEventMembers, List<GroupInvite> groupInvites, List<GroupInvite> newGroupMembers) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
        this.events = events;
        this.eventRequests = eventRequests;
        this.groups = groups;
        this.groupRequests = groupRequests;
        this.eventInvites = eventInvites;
        this.newEventMembers = newEventMembers;
        this.groupInvites = groupInvites;
        this.newGroupMembers = newGroupMembers;
    }

    public User(int userId, String email, String nickname, String password, String aboutMe, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks, List<UserEvent> events, List<EventRequest> eventRequests, List<UserGroup> groups, List<GroupRequest> groupRequests) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
        this.events = events;
        this.eventRequests = eventRequests;
        this.groups = groups;
        this.groupRequests = groupRequests;
    }

    public User(String email, String nickname, String password, String aboutMe, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks, List<UserEvent> events, List<EventRequest> eventRequests, List<UserGroup> groups, List<GroupRequest> groupRequests) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.aboutMe = aboutMe;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
        this.events = events;
        this.eventRequests = eventRequests;
        this.groups = groups;
        this.groupRequests = groupRequests;
    }

    public User(int userId, String email, String aboutMe, String nickname, String password, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks) {
        this.aboutMe = aboutMe;
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
    }
    public User(String email, String nickname, String password, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.bookmarks = bookmarks;
    }

    public User(int userId, String email, String nickname, String password, String firstName, String lastName, String profilePicture) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<SecurityQuestion> getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(List<SecurityQuestion> securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follow> following) {
        this.following = following;
    }

    public List<Follow> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Follow> followed) {
        this.followed = followed;
    }

    public List<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public List<UserEvent> getEvents() {
        return events;
    }

    public void setEvents(List<UserEvent> events) {
        this.events = events;
    }

    public List<EventRequest> getEventRequests() {
        return eventRequests;
    }

    public void setEventRequests(List<EventRequest> eventRequests) {
        this.eventRequests = eventRequests;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public List<GroupRequest> getGroupRequests() {
        return groupRequests;
    }

    public void setGroupRequests(List<GroupRequest> groupRequests) {
        this.groupRequests = groupRequests;
    }

    public List<EventInvite> getEventInvites() {
        return eventInvites;
    }

    public void setEventInvites(List<EventInvite> eventInvites) {
        this.eventInvites = eventInvites;
    }

    public List<EventInvite> getNewEventMembers() {
        return newEventMembers;
    }

    public void setNewEventMembers(List<EventInvite> newEventMembers) {
        this.newEventMembers = newEventMembers;
    }

    public List<GroupInvite> getGroupInvites() {
        return groupInvites;
    }

    public void setGroupInvites(List<GroupInvite> groupInvites) {
        this.groupInvites = groupInvites;
    }

    public List<GroupInvite> getNewGroupMembers() {
        return newGroupMembers;
    }

    public void setNewGroupMembers(List<GroupInvite> newGroupMembers) {
        this.newGroupMembers = newGroupMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(nickname, user.nickname) && Objects.equals(password, user.password) && Objects.equals(aboutMe, user.aboutMe) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(profilePicture, user.profilePicture) && Objects.equals(posts, user.posts) && Objects.equals(securityQuestions, user.securityQuestions) && Objects.equals(likes, user.likes) && Objects.equals(following, user.following) && Objects.equals(followed, user.followed) && Objects.equals(bookmarks, user.bookmarks) && Objects.equals(events, user.events) && Objects.equals(eventRequests, user.eventRequests) && Objects.equals(groups, user.groups) && Objects.equals(groupRequests, user.groupRequests) && Objects.equals(eventInvites, user.eventInvites) && Objects.equals(newEventMembers, user.newEventMembers) && Objects.equals(groupInvites, user.groupInvites) && Objects.equals(newGroupMembers, user.newGroupMembers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, nickname, password, aboutMe, firstName, lastName, profilePicture, posts, securityQuestions, likes, following, followed, bookmarks, events, eventRequests, groups, groupRequests, eventInvites, newEventMembers, groupInvites, newGroupMembers);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
