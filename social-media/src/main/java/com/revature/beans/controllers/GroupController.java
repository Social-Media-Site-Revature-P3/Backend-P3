package com.revature.beans.controllers;

import com.revature.beans.services.EventService;
import com.revature.beans.services.GroupService;
import com.revature.models.Event;
import com.revature.models.Group;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GroupController {
    private GroupService service;

    public GroupController(GroupService groupService) {
        this.service = groupService;
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<Group> getGroupById(@PathVariable Integer groupId) {
        Optional<Group> optionalGroup = this.service.readByGroupId(groupId);
        try {
            optionalGroup.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalGroup.get());
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<Group>> getGroupByName(@PathVariable String name) {
        return ResponseEntity.ok(this.service.readByGroupName(name));
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createGroup(group));
    }

    @PutMapping
    public ResponseEntity<Group> updateGroup(@RequestBody Group group) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createGroup(group));
    }

    @DeleteMapping(value = "/{groupId}")
    public void deleteGroup(@PathVariable Integer groupId) {
        this.service.deleteGroup(groupId);
    }
}
