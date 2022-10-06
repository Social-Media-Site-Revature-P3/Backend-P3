package com.revature.beans.services;

import com.revature.beans.repositories.PostRepository;
import com.revature.beans.repositories.UserRepository;
import com.revature.dtos.Comment;
import com.revature.dtos.FollowedId;
import com.revature.exceptions.UserExistsException;
import com.revature.models.*;
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
class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostService postService;
    @InjectMocks
    private UserService userService;

    @InjectMocks
    private AuthService authService;
    private List<Post> postList;
    private Post post;
    private Comment comment;
    private List<Comment> comments;
    private User user;
    private List<User> users;
    private Like like;
    private List<Like> likes;
    private List<Follow> follows;
    private List<FollowedId> followedIds;
    private Bookmark bookmark;
    private List<Bookmark> bookmarks;

    @BeforeEach
    public void setup() {
        user = new User(1, "kidu@bishaw.com", "konjo", "pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        users = Arrays.asList(
                new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png"),
                new User(2, "rebecca@gmail.com", "konjo", "pass2", "About Rebecca", "Rebecca", "Candelaria", "../src/img/avatar6.png"),
                new User(3, "Dayna@yahoo.com", "konjo", "pass3", "About Dayna", "Dayna", "Johns", "../src/img/avatar5.png")
        );
        post = new Post(1,"text one", "../src/img/avatar7.png", "post", LocalDateTime.now(), LocalDateTime.now(), postList,user,likes,bookmarks);
        postList = Arrays.asList(

                new Post(1,"text one","../src/img/avatar7.png",  "post one", LocalDateTime.now(), LocalDateTime.now(), postList,user,likes,bookmarks),
                new Post(2,"text two", "../src/img/avatar6.png", "post two", LocalDateTime.now(), LocalDateTime.now(),postList,user,likes,bookmarks),
                new Post(3,"text three", "../src/img/avatar5.png", "post three", LocalDateTime.now(), LocalDateTime.now(), postList,user,likes,bookmarks)
        );
        like = new Like(1, true, post, user);
        likes = Arrays.asList(
                new Like(1, true, post, user),
                new Like(2, true, post, user)
        );
        followedIds = Arrays.asList(new FollowedId(1), new FollowedId(2));
        follows = Arrays.asList(
                new Follow(1, users.get(1), users.get(2)),
                new Follow(2, users.get(2), users.get(1))
        );
        comment = new Comment(1,1);
        comments = Arrays.asList(
                new Comment(1, 1),
                new Comment(2, 1),
                new Comment(3, 1)
        );
        bookmark = new Bookmark(1, post,user);
        bookmarks = Arrays.asList(
                new Bookmark(1, post,user),
                new Bookmark(2, post,user)
        );
    }

    @DisplayName("JUnit test for findByUserId method")
    @Test
    void findByUserId() {
        int userId = 1;
        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        User savedUser = userService.findByUserId(user.getUserId()).get();

        assertThat(savedUser).isNotNull();
    }

    @DisplayName("JUnit test for findByCredentials method")
    @Test
    void findByCredentials() {
        given(userRepository.findByEmailAndPassword("kidu@bishaw.com", "pass1"))
                .willReturn(Optional.of(user));

        User savedUser =  userService.findByCredentials(user.getEmail(), user.getPassword()).get();

        assertThat(savedUser).isNotNull();
    }

    @Test
    void findByEmail() {
        given(userRepository.findByEmail("kidu@bishaw.com")).willReturn(Optional.of(user));

        User savedUser = userService.findByEmail(user.getEmail()).get();
        assertThat(savedUser).isNotNull();
    }

    @DisplayName("JUnit test for findByFullName method")
    @Test
    void findByFullName() {

        given(userRepository.findByFirstNameAndLastName("Kidist", "Bishaw"))
                .willReturn(List.of(user));

        List<User> userList = userService.findByFullName(user.getFirstName(), user.getLastName());

        assertThat(userList).isNotNull();
    }

    @DisplayName("JUnit test for findByFirstOrLastName method")
    @Test
    void findByFirstOrLastName() {
        given(userRepository.findByFirstNameOrLastName("Bishaw"))
                .willReturn(List.of(user));

        List<User> userList = userService.findByFirstOrLastName(user.getLastName());

        assertThat(userList).isNotNull();
    }

    @DisplayName("JUnit test for findAll method")
    @Test
    void findAll() {
        given(userRepository.findAll()).willReturn(users);

        List<User> userList = userService.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(3);
    }

    @DisplayName("JUnit test for saveUser method which throws exception")
    @Test
    void registerwithException() throws UserExistsException{
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.of(user));

        org.junit.jupiter.api.Assertions.assertThrows(UserExistsException.class, () -> {
            userService.save(user);
        });
        verify(userRepository, never()).save(any(User.class));
    }


    @DisplayName("JUnit test for saveUser method")
    @Test
    void save() {
        given(userRepository.findByEmail(user.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(user)).willReturn(user);

        User savedUser = userService.save(user);
        assertThat(savedUser).isNotNull();
    }


    @DisplayName("JUnit test for updateUser method")
    @Test
    void update() {

        given(userService.update(user)).willReturn(user);
        user.setEmail("rebecan11@gmail.com");
        user.setAboutMe("Revature Associate looking to meet other employees on Revii");

        User updatedUser = userService.update(user);

        assertThat(updatedUser.getEmail()).isEqualTo("rebecan11@gmail.com");
        assertThat(updatedUser.getAboutMe()).isEqualTo("Revature Associate looking to meet other employees on Revii");
    }

    @Test
    void deleteUser() {
        int userId = 1; int postId = 1;

        willDoNothing().given(postRepository).deleteComment(postId);
        postService.deleteComment(postId);
        verify(postRepository, times(1)).deleteComment(postId);
        System.out.println(postRepository.findByUser_UserId(userId).size());

        willDoNothing().given(userRepository).deleteById(userId);

        userRepository.deleteById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}