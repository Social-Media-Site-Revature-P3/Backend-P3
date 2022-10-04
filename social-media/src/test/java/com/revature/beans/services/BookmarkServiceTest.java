package com.revature.beans.services;

import com.revature.beans.repositories.BookmarkRepository;
import com.revature.models.Bookmark;
import com.revature.models.Post;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.awt.print.Book;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations={"social-media:src/main/webapp/WEB-INF/application-context.xml"})
public class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    private Bookmark bookmark;
    private List<Bookmark> bookmarks;
    private User user;
    private List<User> users;
    private Post post;
    private List<Post> posts;

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


        bookmark = new Bookmark(1, this.post, this.user);
        bookmarks = Arrays.asList(
                new Bookmark(1, posts.get(0), users.get(0)),
                new Bookmark(2, posts.get(1), users.get(0)),
                new Bookmark(3, posts.get(0), users.get(1)),
                new Bookmark(4, posts.get(2), users.get(2))
        );
    }

    @DisplayName("JUnit test for readByBookmarkId")
    @Test
    public void givenBookmarkId_whenFindByBookmarkId_thenReturnBookmarkObject() {
        given(bookmarkRepository.findById(1)).willReturn(Optional.of(bookmark));

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
}
