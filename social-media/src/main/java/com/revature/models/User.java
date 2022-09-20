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
    private String email;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<SecurityQuestion> securityQuestions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Like> likes;

    @OneToMany(mappedBy = "followerUser")
    @JsonIgnore
    private List<Follow>  following;

    @OneToMany(mappedBy = "followedUser")
    @JsonIgnore
    private List<Follow> followed;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "bookmarks",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")})
    private Set<Post> postBookmarks = new HashSet<>();

    public User() {
    }

    public User(int userId, String email, String nickname, String password, String firstName, String lastName, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, Set<Post> postBookmarks) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.postBookmarks = postBookmarks;
    }

    public User(String email, String nickname, String password, String firstName, String lastName, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, Set<Post> postBookmarks) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.securityQuestions = securityQuestions;
        this.likes = likes;
        this.following = following;
        this.followed = followed;
        this.postBookmarks = postBookmarks;
    }

    public User(int i, String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<Post> getPostBookmarks() {
        return postBookmarks;
    }

    public void setPostBookmarks(Set<Post> postBookmarks) {
        this.postBookmarks = postBookmarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(nickname, user.nickname) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(posts, user.posts) && Objects.equals(securityQuestions, user.securityQuestions) && Objects.equals(likes, user.likes) && Objects.equals(following, user.following) && Objects.equals(followed, user.followed) && Objects.equals(postBookmarks, user.postBookmarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, nickname, password, firstName, lastName, posts, securityQuestions, likes, following, followed, postBookmarks);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                ", securityQuestions=" + securityQuestions +
                ", likes=" + likes +
                ", following=" + following +
                ", followed=" + followed +
                ", postBookmarks=" + postBookmarks +
                '}';
    }
}
