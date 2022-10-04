package com.revature.beans.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.beans.repositories.PostRepository;
import com.revature.dtos.Comment;
import com.revature.dtos.FollowedId;
import org.springframework.stereotype.Service;

import com.revature.models.Post;

@Service
public class PostService {

	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public Optional<Post> readByPostId(Integer postId) {
		return this.postRepository.findById(postId);
	}

	public List<Post> readByUserId(Integer userId) {
		return this.postRepository.findByUser_UserId(userId);
	}

	public List<Post> readByOriginalPost(Integer userId) {
		return this.postRepository.findOriginalPost(userId);
	}

	public List<Post> readByFollowed(List<FollowedId> followedIds) {
		List<Post> followedList = new LinkedList<>();
		for(FollowedId id : followedIds) {
			List<Post> postList = this.postRepository.findByUser_UserId(id.getFollowedId());
			for(Post post: postList){
				Optional<Post> optionalPost = this.postRepository.findById(post.getPostId());
				try {
					if(optionalPost.isPresent()) {
						Post userPost = optionalPost.get();
						followedList.add(userPost);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return followedList;
	}

	public List<Post> readByComments(Integer postId) {
		return this.postRepository.findCommentsByPost(postId);
	}

	public List<Post> readAll() {
		return this.postRepository.findAll();
	}

	public Post upsert(Post post) {
		return this.postRepository.save(post);
	}

	public Comment createComment(Comment comment) {
		return this.postRepository.saveComment(comment.getCommentId(), comment.getPostId());
	}

	public void update(Integer postId, String image, String text, String title, Integer userId) {
		this.postRepository.updatePostById(postId, image, text, title, userId);
	}

	public void deletePost(Integer postId) {
		this.postRepository.deleteById(postId);
	}

	public void deleteComment(Integer postId) {
		this.postRepository.deleteComment(postId);
		this.postRepository.deleteById(postId);
	}
}
