package com.revature.beans.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.revature.beans.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	public List<Post> readByFollowed(List<Integer> followedIds) {
		List<Post> followedList = new LinkedList<>();
		for(Integer id : followedIds) {
			Optional<Post> optionalPost = this.postRepository.findById(id);
			try {
				if(optionalPost.isPresent()) {
					Post post = optionalPost.get();
					followedList.add(post);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return followedList;
	}

	public List<Post> readAll() {
		return this.postRepository.findAll();
	}

	public Post upsert(Post post) {
		return this.postRepository.save(post);
	}

	public void deletePost(Integer postId) {
		this.postRepository.deleteById(postId);
	}
}
