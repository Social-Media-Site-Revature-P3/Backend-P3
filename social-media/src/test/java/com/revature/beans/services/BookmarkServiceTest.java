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

import java.util.List;
import java.util.Optional;

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
    private Post post;

    @BeforeEach
    public void setup() {
        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        User user2 = new User(2, "rebecca@gmail.com", "konjo", "pass2", "About Rebecca", "Rebecca", "Candelaria", "../src/img/avatar6.png");

        post = new Post(1, "this is the post text", "Post image", "Post Title", false);
        bookmark = new Bookmark(1, this.post, this.user);
    }

    @DisplayName("JUnit test for readByBookmarkId")
    @Test
    public void givenBookmarkId_whenFindByBookmarkId_thenReturnBookmarkObject() {
        given(bookmarkRepository.findById(1)).willReturn(Optional.of(bookmark));

        Bookmark savedBookmark = bookmarkService.readByBookmarkId(bookmark.getBookmarkId()).get();

        assertThat(savedBookmark).isNotNull();
    }
}
