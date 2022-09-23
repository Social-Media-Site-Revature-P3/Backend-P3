package com.revature.beans.services;

import com.revature.beans.repositories.SecurityQuestionRepository;
import com.revature.models.SecurityQuestion;
import com.revature.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class SecurityServiceTest {

    @Mock
    private SecurityQuestionRepository securityQuestionRepository;

    @InjectMocks
    private SecurityService securityService;

    private SecurityQuestion securityQuestion;

    private List<SecurityQuestion> securityQuestions;

    private User user;

    @BeforeEach
    public void setup(){

        user = new User(1, "kidu@bishaw.com", "konjo","pass1", "It's me", "Kidist", "Bishaw", "../src/img/avatar7.png");

        securityQuestion = new SecurityQuestion(1, "What was the name of your childhood best friend?", "Chloe Smith", user);

        securityQuestions = Arrays.asList(
                new SecurityQuestion(1, "What was the name of your childhood best friend?", "Chloe Smith", user),
                new SecurityQuestion(2, "What city were you born in?", "Queens", user),
                new SecurityQuestion(3, "What is your mother's maiden name?", "Santana", user)
        );

    }

    @DisplayName("JUnit test for readAllSecurityQuestions method")
    @Test
    public void readAllSecurityQuestions_thenReturnSecurityQuestionObject() {
        given(securityQuestionRepository.findAll()).willReturn(securityQuestions);

        List<SecurityQuestion> securityQuestions = securityService.readAllSecurityQuestions();

        assertThat(securityQuestions).isNotNull();
        assertThat(securityQuestions.size()).isEqualTo(3);

    }

    @DisplayName("JUnit test for readByUserId method")
    @Test
    public void readByUserId() {

        given(securityQuestionRepository.findByUser_UserId(1)).willReturn(List.of(securityQuestion));

        List <SecurityQuestion> listOfQuestions = securityService.readByUserId(user.getUserId());

        assertThat(listOfQuestions).isNotNull();
    }
    @DisplayName("JUnit test for findSecurityQuestion method")
    @Test
    public void findSecurityQuestion() {
        given(securityQuestionRepository.findById(1)).willReturn(Optional.of(securityQuestion));

        SecurityQuestion securityQuestion1 = securityService.findSecurityQuestion(securityQuestion.getQuestionId()).get();

        assertThat(securityQuestion1).isNotNull();
    }
//Not sure how to complete this testing @TODO

    @DisplayName("JUnit test for readByQuestionAndAnswer")
    @Test
    public void readByQuestionAndAnswer() throws Exception {
        given(securityQuestionRepository.findByUser_UserId(1)).willReturn(List.of(securityQuestion));

        SecurityQuestion securityQuestion1 = securityService.readByQuestionAndAnswer(securityQuestion, user.getUserId());

        assertThat(securityQuestion1).isNotNull();

    }

    @DisplayName("JUnit testing for createSecurityQuestion method")
    @Test
    public void createSecurityQuestion() {
        given(securityQuestionRepository.save(securityQuestion)).willReturn(securityQuestion);

        SecurityQuestion savedSecurityQuestion = securityService.createSecurityQuestion(securityQuestion);

        assertThat(savedSecurityQuestion).isNotNull();

    }
    @DisplayName("JUnit test for updateSecurityQuestion method")
    @Test
    public void updateSecurityQuestion() {
        given(securityQuestionRepository.save(securityQuestion)).willReturn(securityQuestion);
        securityQuestion.setQuestion("What is your favorite color?");
        securityQuestion.setAnswer("Yellow");

        SecurityQuestion updatedSecurityQuestion = securityService.createSecurityQuestion(securityQuestion);

        assertThat(updatedSecurityQuestion.getQuestion()).isEqualTo("What is your favorite color?");
        assertThat(updatedSecurityQuestion.getAnswer()).isEqualTo("Yellow");
    }

    @DisplayName("JUnit testing for deleteById method")
    @Test
    public void deleteById() {

        Integer questionId = 1;

        willDoNothing().given(securityQuestionRepository).deleteById(questionId);

        securityService.deleteById(questionId);

        verify(securityQuestionRepository, times(1)).deleteById(questionId);

    }

    @DisplayName("JUnit testing for deleteBySecurityQuestion method")
    @Test
    public void deleteBySecurityQuestion() {

        willDoNothing().given(securityQuestionRepository).deleteByQuestion(securityQuestion);

        securityService.deleteBySecurityQuestion(securityQuestion);

        verify(securityQuestionRepository, times(1)).deleteByQuestion(securityQuestion);

    }
}