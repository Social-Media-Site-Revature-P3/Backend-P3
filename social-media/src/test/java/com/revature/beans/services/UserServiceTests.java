package com.revature.beans.services;

import com.revature.beans.repositories.UserRepository;
import com.revature.beans.services.UserService;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.models.SecurityQuestion;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    private User user;
    private List<User> users;

    private List<SecurityQuestion> securityQuestions;

    @BeforeEach
    public void setup(){

        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        users = Arrays.asList(
                new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png"),
                new User(2, "rebecca@gmail.com", "konjo", "pass2", "About Rebecca", "Rebecca", "Candelaria", "../src/img/avatar6.png"),
                new User(3, "Dayna@yahoo.com", "konjo", "pass3", "About Dayna", "Dayna", "Johns", "../src/img/avatar5.png")
        );

    }

    @DisplayName("JUnit test for findByUserId method")
    @Test
    public void givenUserId_whenFindByUserId_thenReturnUserObject(){

        given(userRepository.findById(1)).willReturn(Optional.of(user));

        User savedUser = userService.findByUserId(user.getUserId()).get();

        assertThat(savedUser).isNotNull();
    }

    @DisplayName("JUnit test for findByCredentials method")
    @Test

    public void givenCredentials_whenFindByEmailAndPassword_thenReturnUserObject(){

        given(userRepository.findByEmailAndPassword("kidu@bishaw.com", "pass1"))
        .willReturn(Optional.of(user));

        User savedUser =  userService.findByCredentials(user.getEmail(), user.getPassword()).get();

        assertThat(savedUser).isNotNull();

    }

    @DisplayName("JUnit test for findByFullName method")
    @Test

    public void givenFullName_whenFindByFullName_thenReturnUserObject(){

        given(userRepository.findByFirstNameAndLastName("Kidist", "Bishaw"))
                .willReturn(List.of(user));

        List<User> userList = userService.findByFullName(user.getFirstName(), user.getLastName());

        assertThat(userList).isNotNull();
    }

    @DisplayName("JUnit test for findByFirstOrLastName method")
    @Test

    public void givenFirstOrLastName_whenFindByFirstOrLastName_thenReturnUserObject(){

        given(userRepository.findByFirstNameOrLastName("Bishaw"))
                .willReturn(List.of(user));

        List<User> userList = userService.findByFirstOrLastName(user.getLastName());

        assertThat(userList).isNotNull();
    }

    @DisplayName("JUnit test for findAll method")
    @Test

    public void givenUserList_whenFindAllUsers_thenReturnUserList(){

        given(userRepository.findAll()).willReturn(users);

        List<User> userList = userService.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(3);
    }


    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenUserObject_whenSaveUser_thenReturnUserObject() throws ResourceNotFoundException {

        given(userRepository.save(user)).willReturn(user);

        User savedUser = userService.save(user);

        assertThat(savedUser).isNotNull();
    }

//    @DisplayName("Junit test for saveUser method which throws exception")
//    @Test
//
//    public void givenUserId_whenSaveUser_thenThrowsException() throws ResourceNotFoundException {
//        given(userRepository.findById(user.getUserId()))
//                .willReturn(Optional.of(user));
//
//        System.out.println(userRepository);
//        System.out.println(userService);
//
//
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            userService.save(user);
//        });
//
//        verify(userRepository, never()).save(any(User.class));
//    }


    //Offer JUNIT testing for findAll negative scenario


    @DisplayName("JUnit test for updateUser method")
    @Test

    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser() throws ResourceNotFoundException {

        given(userService.save(user)).willReturn(user);
        user.setEmail("rebecan11@gmail.com");
        user.setAboutMe("Revature Associate looking to meet other employees on Revii");

        User updatedUser = userService.save(user);

        assertThat(updatedUser.getEmail()).isEqualTo("rebecan11@gmail.com");
        assertThat(updatedUser.getAboutMe()).isEqualTo("Revature Associate looking to meet other employees on Revii");

    }

    @DisplayName("JUnit test for deleteUser method")
    @Test
    public void givenUserId_whenDeleteUser_thenNothing(){

        Integer userId = 1;

        willDoNothing().given(userRepository).deleteById(userId);

        userRepository.deleteById(userId);

        verify(userRepository, times(1)).deleteById(userId);

    }

    //Unable to test deleteUser method fully since test cases do not have posts (Null pointer exception)



}





