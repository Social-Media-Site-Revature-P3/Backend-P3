package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "posts")
public class Post {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id", updatable = false)
    private int postId;
	private String text;
	private String imageUrl;
	private String title;
	private boolean comment;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> comments;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Like> likes;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Bookmark> bookmarks;

	public Post() {
	}

	public Post(int postId, String text, String imageUrl, String title, boolean comment, LocalDateTime createDateTime, LocalDateTime updateDateTime, List<Post> comments, User user, List<Like> likes, List<Bookmark> bookmarks) {
		this.postId = postId;
		this.text = text;
		this.imageUrl = imageUrl;
		this.title = title;
		this.comment = comment;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
		this.bookmarks = bookmarks;
	}

	public Post(String text, String imageUrl, String title, boolean comment, LocalDateTime createDateTime, LocalDateTime updateDateTime, List<Post> comments, User user, List<Like> likes, List<Bookmark> bookmarks) {
		this.text = text;
		this.imageUrl = imageUrl;
		this.title = title;
		this.comment = comment;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.comments = comments;
		this.user = user;
		this.likes = likes;
		this.bookmarks = bookmarks;
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

	public boolean isComment() {
		return comment;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
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
		Post post = (Post) o;
		return postId == post.postId && comment == post.comment && Objects.equals(text, post.text) && Objects.equals(imageUrl, post.imageUrl) && Objects.equals(title, post.title) && Objects.equals(createDateTime, post.createDateTime) && Objects.equals(updateDateTime, post.updateDateTime) && Objects.equals(comments, post.comments) && Objects.equals(user, post.user) && Objects.equals(likes, post.likes) && Objects.equals(bookmarks, post.bookmarks);
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, text, imageUrl, title, comment, createDateTime, updateDateTime, comments, user, likes, bookmarks);
	}

	@Override
	public String toString() {
		return "Post{" +
				"postId=" + postId +
				", text='" + text + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", title='" + title + '\'' +
				", comment=" + comment +
				", createDateTime=" + createDateTime +
				", updateDateTime=" + updateDateTime +
				", comments=" + comments +
				", user=" + user +
				", likes=" + likes +
				", bookmarks=" + bookmarks +
				'}';
	}
}