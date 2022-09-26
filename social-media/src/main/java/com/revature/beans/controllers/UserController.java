package com.revature.beans.controllers;

import com.revature.annotations.Authorized;
import com.revature.beans.services.UserService;
import com.revature.dtos.UserFullName;
import com.revature.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    private UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        Optional<User> optionalUser = service.findByUserId(userId);
        try {
            optionalUser.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @PostMapping(value = "/full-name")
    public ResponseEntity<List<User>> getUsersByFullName(@RequestBody UserFullName name) {
        return ResponseEntity.ok(this.service.findByFullName("^[\\s\\S]*"+name.getFirstName()+"[\\s\\S]*$", "^[\\s\\S]*"+name.getLastName()+"[\\s\\S]*$"));
    }

    @PostMapping(value = "/name")
    public ResponseEntity<List<User>> getUsersByFirstOrLastName(@RequestBody UserFullName name) {
        return ResponseEntity.ok(this.service.findByFirstOrLastName(name.getFirstName()));
    }

    @Authorized
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @Authorized
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(this.service.save(user));
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Integer userId) {
        this.service.deleteUser(userId);
    }
}
