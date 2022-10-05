package com.revature.beans.controllers;

import com.revature.beans.services.EventRequestService;
import com.revature.beans.services.GroupRequestService;
import com.revature.models.EventRequest;
import com.revature.models.GroupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group-requests")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GroupRequestController {

    private GroupRequestService service;

    public GroupRequestController(GroupRequestService groupRequestService) {
        this.service = groupRequestService;
    }

    @GetMapping("/{groupRequestId}")
    public ResponseEntity<GroupRequest> getByGroupRequestId(@PathVariable Integer groupRequestId) {
        Optional<GroupRequest> optionalGroupRequest = this.service.readByGroupRequestId(groupRequestId);
        try {
            optionalGroupRequest.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalGroupRequest.get());
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupRequest>> getByGroupId(@PathVariable Integer groupId) {
        return ResponseEntity.ok(this.service.readByGroupId(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupRequest>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.service.readByUserId(userId));
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<GroupRequest>> getByNotAccepted() {
        return ResponseEntity.ok(this.service.readByNotAccepted());
    }

    @GetMapping
    public ResponseEntity<List<GroupRequest>> getAll() {
        return ResponseEntity.ok(this.service.readAll());
    }

    @PostMapping
    public ResponseEntity<GroupRequest> createGroupRequest(@RequestBody GroupRequest groupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createGroupRequest(groupRequest));
    }

    @PutMapping
    public ResponseEntity<GroupRequest> updateGroupRequest(@RequestBody GroupRequest groupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createGroupRequest(groupRequest));
    }

    @DeleteMapping("/{groupRequestId}")
    public void deleteGroupRequest(@PathVariable Integer groupRequestId) {
        this.service.deleteGroupRequest(groupRequestId);
    }
}
