package com.revature.beans.services;

import com.revature.beans.repositories.UserRepository;
import com.revature.exceptions.UserExistsException;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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
    @DisplayName("test method for AuthService findByCredentials method")
    @Test
    void findByCredentials() {
        String email = "kidu@bishaw.com", password = "pass1";

        given(userRepository.findByEmailAndPassword(email, password)).willReturn(Optional.of(user));

        User savedUser = userService.findByCredentials(email, password).get();
        assertThat(savedUser).isNotNull();

    }
    @DisplayName("test method for AuthService register method")
    @Test
    void register() {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
    }
    @DisplayName("test method for registerwithException method")
    @Test
    void registerwithException(){
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        org.junit.jupiter.api.Assertions.assertThrows(UserExistsException.class, () -> {
           userService.save(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }
}

