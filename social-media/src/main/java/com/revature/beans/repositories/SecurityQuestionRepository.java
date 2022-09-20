package com.revature.beans.repositories;

import com.revature.models.SecurityQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestion, Integer> {
    List<SecurityQuestion> findByUser_UserId(Integer userId);

    @Transactional
    void deleteByQuestion(SecurityQuestion securityQuestion);
}
