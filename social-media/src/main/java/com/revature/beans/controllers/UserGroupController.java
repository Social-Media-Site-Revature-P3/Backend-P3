package com.revature.beans.controllers;

import com.revature.beans.services.UserGroupService;
import com.revature.models.UserGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user-groups")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserGroupController {

    private UserGroupService service;

    public UserGroupController(UserGroupService userGroupService) {
        this.service = userGroupService;
    }

    @GetMapping("/{userGroupId}")
    public ResponseEntity<UserGroup> getByUserGroupId(@PathVariable Integer userGroupId) {
        Optional<UserGroup> optionalUserGroup = this.service.readByUserGroupId(userGroupId);
        try {
            optionalUserGroup.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalUserGroup.get());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserGroup>> getByAmin() {
        return ResponseEntity.ok(this.service.readByAdmin());
    }

    @GetMapping("/creator")
    public ResponseEntity<UserGroup> getByCreator() {
        return ResponseEntity.ok(this.service.readByCreator());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<UserGroup>> getByGroupId(@PathVariable Integer groupId) {
        return ResponseEntity.ok(this.service.readByGroupId(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserGroup>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserGroup>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<UserGroup> createUserGroup(@RequestBody UserGroup userGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createUserGroup(userGroup));
    }

    @PutMapping
    public ResponseEntity<UserGroup> updateUserGroup(@RequestBody UserGroup userGroup) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createUserGroup(userGroup));
    }

    @DeleteMapping("/{userGroupId}")
    public void deleteUserGroup(@PathVariable Integer userGroupId) {
        this.service.deleteUserGroup(userGroupId);
    }
}
