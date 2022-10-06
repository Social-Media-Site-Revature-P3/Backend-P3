package com.revature.beans.services;

import com.revature.beans.repositories.EventInviteRepository;
import com.revature.beans.repositories.GroupInviteRepository;
import com.revature.models.EventInvite;
import com.revature.models.GroupInvite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupInviteService {

    private GroupInviteRepository repository;

    public GroupInviteService(GroupInviteRepository groupInviteRepository) {
        this.repository = groupInviteRepository;
    }

    public Optional<GroupInvite> readByGroupInviteId(Integer eventInviteId) {
        return this.repository.findById(eventInviteId);
    }

    public List<GroupInvite> readByGroupId(Integer groupId) {
        return this.repository.findByGroup_GroupId(groupId);
    }

    public List<GroupInvite> readByGroupInviterId(Integer userId) {
        return this.repository.findByGroupInviter_UserId(userId);
    }

    public List<GroupInvite> readByNewMemberId(Integer userId) {
        return this.repository.findByNewGroupMember_UserId(userId);
    }

    public List<GroupInvite> readByNotAccepted() {
        return this.repository.findByNotAccepted();
    }

    public List<GroupInvite> readAll() {
        return this.repository.findAll();
    }

    public GroupInvite createEventInvite(GroupInvite groupInvite) {
        return this.repository.save(groupInvite);
    }

    public void deleteGroupInvite(Integer groupInviteId) {
        this.repository.deleteById(groupInviteId);
    }
}
