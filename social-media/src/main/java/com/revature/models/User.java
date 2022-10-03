package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.awt.print.Book;
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

    public User() {
    }


    public User(int userId, String email, String aboutMe, String nickname, String password, String firstName, String lastName, String profilePicture, List<Post> posts, List<SecurityQuestion> securityQuestions, List<Like> likes, List<Follow> following, List<Follow> followed, List<Bookmark> bookmarks) {
        this.aboutMe=aboutMe;
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

    public User(int userId,String email, String password, String firstName, String lastName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(nickname, user.nickname) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(profilePicture, user.profilePicture) && Objects.equals(posts, user.posts) && Objects.equals(securityQuestions, user.securityQuestions) && Objects.equals(likes, user.likes) && Objects.equals(following, user.following) && Objects.equals(followed, user.followed) && Objects.equals(bookmarks, user.bookmarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, nickname, aboutMe, password, firstName, lastName, profilePicture, posts, securityQuestions, likes, following, followed, bookmarks);
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
                ", aboutMe='" + aboutMe + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
