package com.revature.beans.services;

import com.revature.beans.repositories.SecurityQuestionRepository;
import com.revature.models.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityService {
    private SecurityQuestionRepository SecurityQuestionRepository;

    @Autowired
    public SecurityService(SecurityQuestionRepository SecurityQuestionRepository) {
        this.SecurityQuestionRepository = SecurityQuestionRepository;
    }
    public List<SecurityQuestion> readAllSecurityQuestions() {
        return SecurityQuestionRepository.findAll();
    }

    public List<SecurityQuestion> readByUserId(Integer userId) {
        return SecurityQuestionRepository.findByUser_UserId(userId);
    }
    public Optional<SecurityQuestion> findSecurityQuestion(Integer id) {
        return SecurityQuestionRepository.findById(id);
    }

    public SecurityQuestion readByQuestionAndAnswer(SecurityQuestion securityQuestion, Integer userId) throws Exception {
        List<SecurityQuestion> securityQuestions = SecurityQuestionRepository.findByUser_UserId(userId);
        for(SecurityQuestion checkSecurityQuestion: securityQuestions) {
            if(checkSecurityQuestion.getQuestion().equals(securityQuestion.getQuestion()) && checkSecurityQuestion.getAnswer().equals(securityQuestion.getAnswer())) {
                return checkSecurityQuestion;
            }
        }
        throw new Exception();
    }

    public SecurityQuestion createSecurityQuestion(SecurityQuestion newSecurityQuestion) {
        return SecurityQuestionRepository.save(newSecurityQuestion);
    }
    public SecurityQuestion updateSecurityQuestion(SecurityQuestion updateSecurityQuestion) {
        return SecurityQuestionRepository.save(updateSecurityQuestion);
    }
    public void deleteById(Integer id) {
        SecurityQuestionRepository.deleteById(id);
    }

    public void deleteBySecurityQuestion(SecurityQuestion securityQuestion) {
        SecurityQuestionRepository.deleteByQuestion(securityQuestion);
    }
}