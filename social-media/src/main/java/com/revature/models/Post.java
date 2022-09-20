package com.revature.models;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "posts")
public class Post {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
    private int postId;
	private String text;
	private String imageUrl;
	private String title;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> comments;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private List<Like> likes;

	@ManyToMany(mappedBy = "postBookmarks", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<User> userBookmarks = new HashSet<>();

	public Post() {
	}

	public Post(int postId, String text, String imageUrl, String title, LocalDateTime createDateTime, List<Post> comments, User user, List<Like> likes, Set<User> userBookmarks) {
		this.postId = postId;
		this.text = text;
		this.imageUrl = imageUrl;
		this.title = title;
		this.createDateTime = createDateTime;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
		this.userBookmarks = userBookmarks;
	}

	public Post(String text, String imageUrl, String title, LocalDateTime createDateTime, List<Post> comments, User user, List<Like> likes, Set<User> userBookmarks) {
		this.text = text;
		this.imageUrl = imageUrl;
		this.title = title;
		this.createDateTime = createDateTime;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
		this.userBookmarks = userBookmarks;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public List<Post> getComments() {
		return comments;
	}

	public void setComments(List<Post> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Set<User> getUserBookmarks() {
		return userBookmarks;
	}

	public void setUserBookmarks(Set<User> userBookmarks) {
		this.userBookmarks = userBookmarks;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return postId == post.postId && Objects.equals(text, post.text) && Objects.equals(imageUrl, post.imageUrl) && Objects.equals(title, post.title) && Objects.equals(createDateTime, post.createDateTime) && Objects.equals(comments, post.comments) && Objects.equals(user, post.user) && Objects.equals(likes, post.likes) && Objects.equals(userBookmarks, post.userBookmarks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, text, imageUrl, title, createDateTime, comments, user, likes, userBookmarks);
	}

	@Override
	public String toString() {
		return "Post{" +
				"postId=" + postId +
				", text='" + text + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", title='" + title + '\'' +
				", createDateTime=" + createDateTime +
				", comments=" + comments +
				", user=" + user +
				", likes=" + likes +
				", userBookmarks=" + userBookmarks +
				'}';
	}
}