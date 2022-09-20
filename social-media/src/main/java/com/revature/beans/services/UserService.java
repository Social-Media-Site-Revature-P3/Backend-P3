package com.revature.beans.services;

import com.revature.beans.repositories.UserRepository;
import com.revature.dtos.UserFullName;
import com.revature.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        this.userRepository.deleteById(userId);
    }
}
