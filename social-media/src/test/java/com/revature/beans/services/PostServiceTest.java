package com.revature.beans.services;

import com.revature.beans.repositories.PostRepository;
import com.revature.dtos.Comment;
import com.revature.dtos.FollowedId;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

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
    public void setup(){
        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
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

    @DisplayName("for readByPostId method")
    @Test
    void readByPostId() {
        int postId = 1;

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        Post savedPost = postService.readByPostId(post.getPostId()).get();

        assertThat(savedPost).isNotNull();
    }

    @DisplayName("for readByUserId method")
    @Test
    void readByUserId() {
        int userId = 1;

        given(postRepository.findByUser_UserId(userId)).willReturn(postList);

        List<Post> posts = postService.readByUserId(userId);

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(3);
    }

    @DisplayName("for readByFollowed method")
    @Test
    void readByFollowed() {
        int userId = 1; int postId = 1;
        given(postRepository.findByUser_UserId(userId)).willReturn(postList);
        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        List<Post> posts = postService.readByFollowed(followedIds);

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(1);

    }

    @DisplayName("for readByComments method")
    @Test
    void readByComments() {
        int postId = 1;
        given(postRepository.findCommentsByPost(postId)).willReturn(postList);

        List<Post> posts = postService.readByComments(postId);

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(3);
    }

    @DisplayName("for readAll method")
    @Test
    void readAll() {

        given(postRepository.findAll()).willReturn(postList);

        List<Post> posts = postService.readAll();

        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(3);
    }

    @DisplayName("for upsert method")
    @Test
    void upsert() {
        given(postRepository.save(post)).willReturn(post);
        post.setTitle("setTitle from test");
        post.setText("setText from test");
        Post upsertPost = postService.upsert(post);

        assertThat(upsertPost.getTitle()).isEqualTo("setTitle from test");
        assertThat(upsertPost.getText()).isEqualTo("setText from test");
    }

    @DisplayName("for createComment method")
    @Test
    void createComment() {
        given(postRepository.saveComment(1,1)) .willReturn(comment);
        Comment savedComment = postService.createComment(comment);
        assertThat(savedComment).isNotNull();
    }

    @DisplayName("for update method")
    @Test
    void update() {
        given(postRepository.save(post)).willReturn(post);
        post.setTitle("setTitle from test");
        post.setText("setText from test");
        Post upsertPost = postService.upsert(post);

        assertThat(upsertPost.getTitle()).isEqualTo("setTitle from test");
        assertThat(upsertPost.getText()).isEqualTo("setText from test");
    }

    @DisplayName("for deletePost method")
    @Test
    void deletePost() {
        int postId = 1;

        willDoNothing().given(postRepository).deleteById(postId);

        postService.deletePost(postId);

        verify(postRepository, times(1)).deleteById(postId);
    }

    @DisplayName("for deleteComment method")
    @Test
    void deleteComment() {
        int postId = 1;
        willDoNothing().given(postRepository).deleteComment(postId);
        postService.deleteComment(postId);
        verify(postRepository, times(1)).deleteComment(postId);
    }
}