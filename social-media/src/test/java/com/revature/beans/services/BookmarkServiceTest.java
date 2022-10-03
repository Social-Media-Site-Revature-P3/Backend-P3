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
class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    private User user;
    private Post post;

    private List<Post> posts;
    private List<Like> likes;
    private Bookmark bookmark;
    private List<Bookmark> bookmarks;

    @BeforeEach
    public void setup() {
        user = new User(1, "kidu@bishaw.com", "konjo", "pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        posts = Arrays.asList(

                new Post(1,"text one","../src/img/avatar7.png",  "post one", LocalDateTime.now(), LocalDateTime.now(), posts,user,likes,bookmarks),
                new Post(2,"text two", "../src/img/avatar6.png", "post two", LocalDateTime.now(), LocalDateTime.now(),posts,user,likes,bookmarks),
                new Post(3,"text three", "../src/img/avatar5.png", "post three", LocalDateTime.now(), LocalDateTime.now(), posts,user,likes,bookmarks)
        );
        //like = new Like(1, true, post, user);
        likes = Arrays.asList(
                new Like(1, true, post, user),
                new Like(2, true, post, user)
        );
        post = new Post(1,"text one", "../src/img/avatar7.png", "post", LocalDateTime.now(), LocalDateTime.now(), posts,user,likes,bookmarks);
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

    @DisplayName("for createBookmark method")
    @Test
    void createBookmark() {
        given(bookmarkRepository.save(bookmark)).willReturn(bookmark);

        Bookmark savedBookmark = bookmarkService.createBookmark(bookmark);
        assertThat(savedBookmark).isNotNull();
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