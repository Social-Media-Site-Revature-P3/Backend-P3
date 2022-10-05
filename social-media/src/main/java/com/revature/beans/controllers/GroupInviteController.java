package com.revature.beans.controllers;

import com.revature.beans.services.EventInviteService;
import com.revature.beans.services.GroupInviteService;
import com.revature.models.EventInvite;
import com.revature.models.GroupInvite;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group-invites")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GroupInviteController {

    private GroupInviteService service;

    public GroupInviteController(GroupInviteService groupInviteService) {
        this.service = groupInviteService;
    }

    @GetMapping("/{groupInviteId}")
    public ResponseEntity<GroupInvite> getByEventInviteId(@PathVariable Integer groupInviteId) {
        Optional<GroupInvite> optionalGroupInvite = this.service.readByGroupInviteId(groupInviteId);
        try {
            optionalGroupInvite.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalGroupInvite.get());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupInvite>> getByGroupId(@PathVariable Integer groupId) {
        return ResponseEntity.ok(this.service.readByGroupId(groupId));
    }

    @GetMapping("/group-inviter/{userId}")
    public ResponseEntity<List<GroupInvite>> getByGroupInviterId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByGroupInviterId(userId));
    }

    @GetMapping("/new-group-member/{userId}")
    public ResponseEntity<List<GroupInvite>> getByNewGroupMemberId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByNewMemberId(userId));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<GroupInvite>> getByNotAccepted() {
        return ResponseEntity.ok(this.service.readByNotAccepted());
    }

    @GetMapping
    public ResponseEntity<List<GroupInvite>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<GroupInvite> createEventInvite(@RequestBody GroupInvite groupInvite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventInvite(groupInvite));
    }

    @PutMapping
    public ResponseEntity<GroupInvite> updateEventInvite(@RequestBody GroupInvite groupInvite) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createEventInvite(groupInvite));
    }

    @DeleteMapping("/{groupInviteId}")
    public void deleteGroupInvite(@PathVariable Integer groupInviteId) {
        this.service.deleteGroupInvite(groupInviteId);
    }
}
