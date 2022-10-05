package com.revature.beans.services;

import com.revature.beans.repositories.UserEventRepository;
import com.revature.beans.repositories.UserGroupRepository;
import com.revature.models.UserEvent;
import com.revature.models.UserGroup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    private UserGroupRepository repository;

    public UserGroupService(UserGroupRepository userGroupRepository) {
        this.repository = userGroupRepository;
    }

    public Optional<UserGroup> readByUserGroupId(Integer userGroupId) {
        return this.repository.findById(userGroupId);
    }

    public List<UserGroup> readByAdmin() {
        return this.repository.findByAdmin();
    }

    public UserGroup readByCreator() {
        return this.repository.findByCreator();
    }

    public List<UserGroup> readByGroupId(Integer groupId) {
        return this.repository.findByGroup_GroupId(groupId);
    }

    public List<UserGroup> readByUserId(Integer userId) {
        return this.repository.findByUser_UserId(userId);
    }

    public List<UserGroup> readAll() {
        return this.repository.findAll();
    }

    public UserGroup createUserGroup(UserGroup userGroup) {
        return this.repository.save(userGroup);
    }

    public void deleteUserGroup(Integer userGroupId) {
        this.repository.deleteById(userGroupId);
    }
}
