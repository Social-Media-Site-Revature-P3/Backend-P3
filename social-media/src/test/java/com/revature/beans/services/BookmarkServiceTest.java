package com.revature.beans.services;

import com.revature.beans.repositories.BookmarkRepository;
import com.revature.models.Bookmark;
import com.revature.models.Like;
import com.revature.models.Post;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations={"social-media:src/main/webapp/WEB-INF/application-context.xml"})
public class BookmarkServiceTest {


    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    private User user;
    private List<User> users;
    private Post post;
    private List<Post> posts;
    private List<Like> likes;
    private Bookmark bookmark;
    private List<Bookmark> bookmarks;

    @BeforeEach
    public void setup() {
        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        users = Arrays.asList(
                new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png"),
                new User(2, "rebecca@gmail.com", "konjo", "pass2", "About Rebecca", "Rebecca", "Candelaria", "../src/img/avatar6.png"),
                new User(3, "Dayna@yahoo.com", "konjo", "pass3", "About Dayna", "Dayna", "Johns", "../src/img/avatar5.png")
        );

        post = new Post(1, "this is the post text", "Post image", "Post Title", false);
        posts = Arrays.asList(
                new Post(1, "this is the post text", "Post image", "Post Title", false),
                new Post(2, "this is the second post text", "Post image", "Post Title", false),
                new Post(3, "this is the third post text", "Post image", "Post Title", false)
        );

        user = new User(1, "kidu@bishaw.com", "konjo", "pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");

        //like = new Like(1, true, post, user);
        likes = Arrays.asList(
                new Like(1, true, post, user),
                new Like(2, true, post, user)
        );

        bookmark = new Bookmark(1, post, user);
        bookmarks = Arrays.asList(
                new Bookmark(1, post, user),
                new Bookmark(2, post, user)
        );
    }
    @DisplayName("for readByBookmarkId method")
    @Test
    void readByBookmarkId() {
        int bookmarkId = 1;
        given(bookmarkRepository.findById(bookmarkId)).willReturn(Optional.of(bookmark));
        Bookmark savedBookmark = bookmarkService.readByBookmarkId(bookmark.getBookmarkId()).get();
        assertThat(savedBookmark).isNotNull();
    }

    @DisplayName("JUnit test for readByPostId")
    @Test
    public void givenPostId_WhenFindByPost_PostId_thenReturnBookmarkList() {
        given(bookmarkRepository.findByPost_PostId(1)).willReturn(bookmarks);
        List<Bookmark> newBookmarks = new ArrayList<>();

        for(Bookmark bookmark : bookmarks) {
            bookmarkService.readByPostId(bookmark.getPost().getPostId());
            if(bookmark.getPost().getPostId() == 1) {
                newBookmarks.add(bookmark);
            }
        }

        assertThat(newBookmarks.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for readByUserId")
    @Test
    public void givenUserId_WhenFindByUser_UserId_thenReturnBookmarkList() {
        given(bookmarkRepository.findByUser_UserId(1)).willReturn(bookmarks);
    }

    @DisplayName("JUnit test for readAll")
    @Test
    public void readAllBookmarks_thenReturnListOfBookmarks() {
        given(bookmarkRepository.findAll()).willReturn(bookmarks);
        List<Bookmark> bookmarks = bookmarkService.readAll();
        assertThat(bookmarks.size()).isEqualTo(4);
    }

    @DisplayName("JUnit test for createBookmark")
    @Test
    public void createBookmark() {
        given(bookmarkRepository.save(bookmark)).willReturn((bookmark));

        Bookmark savedBookmark = bookmarkService.createBookmark(bookmark);

        assertThat(savedBookmark).isNotNull();
    }

    @DisplayName("JUnit test for deleteBookmark")
    @Test
    public void deleteBookmark_WhenBookmarkId() {
        willDoNothing().given(bookmarkRepository).deleteById(1);

        bookmarkService.deleteBookmark(1);

        verify(bookmarkRepository, times(1)).deleteById(1);
    }

    @DisplayName("for readByPostId method")
    @Test
    void readByPostId() {
        int postId = 1;
        given(bookmarkRepository.findByPost_PostId(postId)).willReturn(bookmarks);

        List<Bookmark> bookmarkList = bookmarkService.readByPostId(postId);

        assertThat(bookmarkList).isNotNull();
        assertThat(bookmarkList.size()).isEqualTo(2);

    }

    @DisplayName("for readByUserId method")
    @Test
    void readByUserId() {
        int userId = 1;

        given(bookmarkRepository.findByUser_UserId(userId)).willReturn(bookmarks);

        List<Bookmark> bookmarkList = bookmarkService.readByUserId(userId);

        assertThat(bookmarkList).isNotNull();
        assertThat(bookmarkList.size()).isEqualTo(2);
    }

    @DisplayName("for readAll method")
    @Test
    void readAll() {
        given(bookmarkRepository.findAll()).willReturn(bookmarks);

        List <Bookmark> bookmarkList = bookmarkService.readAll();

        assertThat(bookmarkList).isNotNull();
        assertThat(bookmarkList.size()).isEqualTo(2);
    }

    @DisplayName("for deleteBookmark method")
    @Test
    void deleteBookmark() {
        int bookmarkId = 1;

        willDoNothing().given(bookmarkRepository).deleteById(bookmarkId);
        bookmarkService.deleteBookmark(bookmarkId);

        verify(bookmarkRepository, times(1)).deleteById(bookmarkId);

    }
}

