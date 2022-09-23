package com.revature.beans.services;

import com.revature.beans.repositories.PostRepository;
import com.revature.beans.repositories.UserRepository;
import com.revature.dtos.UserFullName;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.Post;
import com.revature.models.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public Optional<User> findByUserId(Integer userId) {
        return this.userRepository.findById(userId);
    }

    public Optional<User> findByCredentials(String email, String password) {
        return this.userRepository.findByEmailAndPassword(email, password);
    }

    public List<User> findByFullName(String firstName, String lastName) {
        return this.userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> findByFirstOrLastName(String name) {
        return this.userRepository.findByFirstNameOrLastName(name);
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User save(User user) throws ResourceNotFoundException {
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser.isPresent()){
            throw new ResourceNotFoundException("User already exists" + user.getEmail());
        }
        return this.userRepository.save(user);
    }



    public void deleteUser(Integer userId) {
        List<Post> postList = this.postRepository.findByUser_UserId(userId);
        for(Post post: postList) {
            if(this.postRepository.findById(post.getPostId()).isPresent()) {
                if(this.postRepository.findPostIfComment(post.getPostId()).isPresent()) {
                    this.postRepository.deleteComment(post.getPostId());
                    this.postRepository.deleteById(post.getPostId());
                }else {
                    this.postRepository.deleteById(post.getPostId());
                }
            }
        }
        this.userRepository.deleteById(userId);
    }
}