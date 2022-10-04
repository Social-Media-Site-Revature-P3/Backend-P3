package com.revature.beans.services;

import com.revature.beans.repositories.FollowRepository;
import com.revature.beans.services.FollowService;
import com.revature.models.Follow;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;import java.util.Arrays;
import java.util.List;
import java.util.Optional;import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Test
    void readByFollowId() {
    }
    @Mock
    private FollowRepository followRepository;  @InjectMocks
    private FollowService followService;
    private Follow follow;
    private List<Follow> followList;
    private User user, user1;
    private List<User> userList;  private User followedUser;  @BeforeEach
    public void setup() {
        follow = new Follow(44453, user, user1);
        followList = Arrays.asList(
                new Follow(44453, user, user1),
                new Follow(78932, user1,user)
        );
        user = new User(39696,"user@gmail.com", "konjo", "password", "John", "Doe");
        user1 = new User(39695,"user@gmail.com","konjo", "password", "James", "Doe");
    /* userList = Arrays.asList(
        new User(44453, "user@gmail.com", "password", "Jane", "Doe"),
        new User(39696,"user@gmail.com","password", "Adam", "Doe"),
        new User(39695,"user@gmail.com","password", "Jack", "Doe"));    */
    }  //JUnit test for read by follow ID method
    @DisplayName("Test should get a follow By the Follow Id")
    @Test
    public void getAFollowByFollowId() {
        //given
        int followId = 44453;
        given(followRepository.findById(followId)).willReturn(Optional.of(follow));
        Follow savedFollow = followService.readByFollowId(follow.getFollowId()).get();
        //when
        assertThat(savedFollow).isNotNull();
    }  //JUnit test for read by followed ID method
    @DisplayName("Test should get a follow by the followed Id")
    @Test
    public void getAFollowByFollowedId() {
        //given
        int followedId = 12345;
        given(followRepository.findByFollowedUser_UserId(followedId)).willReturn((followList));
        List <Follow> followList = followService.readByFollowedId(followedId);
        //when
        assertThat(followList).isNotNull();
        assertThat(followList.size()).isEqualTo(2);  }  //JUnit test for read by follower ID method
    @DisplayName("Test should get a follow by the follower Id")
    @Test
    public void getAFollowByFollowerId() {
        //given
        int followerId = 44453;
        given(followRepository.findByFollowerUser_UserId(followerId)).willReturn(followList);
        List <Follow> followList = followService.readByFollowerId(followerId);
        //when
        assertThat(followList).isNotNull();
        assertThat(followList.size()).isEqualTo(2);
    }  //JUnit test for read all followers on follower list
    @DisplayName("Test should read all followers on followers list")
    @Test
    public void readAllFollowers() {
        //given
        given(followRepository.findAll()).willReturn(List.of(follow));    List<Follow> followList = followService.readAll();    assertThat(followList).isNotNull();  }  //JUnit test for create Follow
    @DisplayName("Test should create followers")
    @Test
    public void createFollow() {
        //given
        given(followRepository.save(follow)).willReturn(follow);    Follow savedFollow = followService.createFollow(follow);    assertThat(savedFollow).isNotNull();  }  //JUnit test for delete Follow
    @DisplayName("Test should delete followers")
    @Test
    public void deleteFollow() {
        //given
        int followId = 33942;    willDoNothing().given(followRepository).deleteById(followId);    followService.deleteFollow(followId);    verify(followRepository, times(1)).deleteById(followId);
    }
}