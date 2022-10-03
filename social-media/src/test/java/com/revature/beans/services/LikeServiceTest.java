package com.revature.beans.services;

import com.revature.beans.repositories.LikeRepository;
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
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @InjectMocks
    private LikeService likeService;

    private Like like;
    private User user;
    private Post post;
    private List<Like> likes;

    @BeforeEach
    public void setup(){
        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");
        post = new Post(1,"text one","../src/img/avatar7.png",  "post one", LocalDateTime.now(), LocalDateTime.now());
        like = new Like(1, true, post, user);
        likes = Arrays.asList(
                new Like(1, true, post, user),
                new Like(2, true, post, user)
        );
    }

    @DisplayName("Test for readByLikeId method")
    @Test
    void readByLikeId() {

        given(likeRepository.findById(1)).willReturn(Optional.of(like));
        Like savedLike = likeService.readByLikeId(like.getLikeId()).get();

        assertThat(savedLike).isNotNull();
    }

    @DisplayName("Test for readByPostId method")
    @Test
    void readByPostId() {
        given(likeRepository.findByPost_PostId(1)).willReturn(likes);
        List<Like> likeList = likeService.readByPostId(1);

        assertThat(likeList).isNotNull();
        assertThat(likeList.size()).isEqualTo(2);
    }

    @DisplayName("Test for readByUserId method")
    @Test
    void readByUserId() {
        given(likeRepository.findByUser_UserId(1)).willReturn(likes);
        List<Like> likeList = likeService.readByUserId(1);

        assertThat(likeList).isNotNull();
        assertThat(likeList.size()).isEqualTo(2);
    }

    @DisplayName("Test for readAll method")
    @Test
    void readAll() {
        given(likeRepository.findAll()).willReturn(likes);
        List<Like> likeList = likeService.readAll();

        assertThat(likeList).isNotNull();
        assertThat(likeList.size()).isEqualTo(2);
    }

    @DisplayName("Test for createLike method")
    @Test
    void createLike() {
        given(likeRepository.save(like)).willReturn(like);
        Like savedLike = likeService.createLike(like);

        assertThat(savedLike).isNotNull();
    }

    @DisplayName("Test for deleteLike method")
    @Test
    void deleteLike() {
        int likeId = 1;
        willDoNothing().given(likeRepository).deleteById(likeId);
        likeService.deleteLike(likeId);

        verify(likeRepository, times(1)).deleteById(likeId);
    }
}