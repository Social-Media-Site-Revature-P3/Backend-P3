package com.revature.beans.services;

import com.revature.beans.repositories.UserRepository;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private AuthService authService;
    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1, "kidu@bishaw.com", "konjo", "pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
    }
    @Test
    void findByCredentials() {
        String email = "kidu@bishaw.com", password = "pass1";
        given(userRepository.findByEmailAndPassword(email, password)).willReturn(Optional.of(user));

        User savedUser = userService.findByCredentials((user.getEmail()), user.getPassword()).get();

        assertThat(savedUser).isNotNull();
    }

    @Test
    void register() throws ResourceNotFoundException {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
    }

    @Test
    void registerwithException() throws ResourceNotFoundException {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
           userService.save(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }
}

