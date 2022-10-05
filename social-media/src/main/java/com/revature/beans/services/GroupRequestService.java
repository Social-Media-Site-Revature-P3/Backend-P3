package com.revature.beans.services;


import com.revature.beans.repositories.GroupRequestRepository;
import com.revature.models.EventRequest;
import com.revature.models.Group;
import com.revature.models.GroupRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupRequestService {

    private GroupRequestRepository repository;

    public GroupRequestService(GroupRequestRepository groupRequestRepository) {
        this.repository = groupRequestRepository;
    }

    public Optional<GroupRequest> readByGroupRequestId(Integer groupRequestId) {
        return this.repository.findById(groupRequestId);
    }

    public List<GroupRequest> readByGroupId(Integer groupId) {
        return this.repository.findByGroup_GroupId(groupId);
    }

    public List<GroupRequest> readByUserId(Integer userId) {
        return this.repository.findByUser_UserId(userId);
    }

    public List<GroupRequest> readByNotAccepted() {
        return this.repository.findByNotAccepted();
    }

    public List<GroupRequest> readAll() {
        return this.repository.findAll();
    }

    public GroupRequest createGroupRequest(GroupRequest groupRequest) {
        return this.repository.save(groupRequest);
    }

    public void deleteGroupRequest(Integer groupRequestId) {
        this.repository.deleteById(groupRequestId);
    }
}
