package com.revature.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "security_questions")
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public SecurityQuestion() {
    }

    public SecurityQuestion(int questionId, String question, String answer, User user) {
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
        this.user = user;
    }

    public SecurityQuestion(String question, String answer, User user) {
        this.question = question;
        this.answer = answer;
        this.user = user;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityQuestion that = (SecurityQuestion) o;
        return questionId == that.questionId && Objects.equals(question, that.question) && Objects.equals(answer, that.answer) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question, answer, user);
    }

    @Override
    public String toString() {
        return "SecurityQuestion{" +
                "questionId=" + questionId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", user=" + user +
                '}';
    }
}