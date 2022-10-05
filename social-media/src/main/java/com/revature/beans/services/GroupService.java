package com.revature.beans.services;

import com.revature.beans.repositories.GroupRepository;
import com.revature.beans.repositories.PostRepository;
import com.revature.models.Group;
import com.revature.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private GroupRepository repository;
    private PostRepository postRepository;

    public GroupService(GroupRepository groupRepository, PostRepository postRepository) {
        this.repository = groupRepository;
        this.postRepository = postRepository;
    }

    public Optional<Group> readByGroupId(Integer groupId) {
        return this.repository.findById(groupId);
    }

    public List<Group> readByGroupName(String name) {
        return this.repository.findByName(name);
    }

    public List<Group> readAll() {
        return this.repository.findAll();
    }

    public Group createGroup(Group group) {
        return this.repository.save(group);
    }

    public void deleteGroup(Integer groupId) {
        List<Post> postList = this.postRepository.findByGroup_GroupId(groupId);
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
        this.repository.deleteById(groupId);
    }
}
