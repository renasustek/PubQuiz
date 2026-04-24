package com.github.renas.PubQuiz.quiz.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class QuestionDao {

    @Id
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "correct_answer", nullable = false, length = 100)
    private String correctAnswer;

    @Column(name = "incorrect_answer_1", nullable = false, length = 100)
    private String incorrectAnswer1;

    @Column(name = "incorrect_answer_2", nullable = false, length = 100)
    private String incorrectAnswer2;

    @Column(name = "incorrect_answer_3", nullable = false, length = 100)
    private String incorrectAnswer3;

    public QuestionDao() {
    }

    public QuestionDao(String question, String correctAnswer, String incorrectAnswer1, String incorrectAnswer2, String incorrectAnswer3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer1 = incorrectAnswer1;
        this.incorrectAnswer2 = incorrectAnswer2;
        this.incorrectAnswer3 = incorrectAnswer3;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswer1() {
        return incorrectAnswer1;
    }

    public void setIncorrectAnswer1(String incorrectAnswer1) {
        this.incorrectAnswer1 = incorrectAnswer1;
    }

    public String getIncorrectAnswer2() {
        return incorrectAnswer2;
    }

    public void setIncorrectAnswer2(String incorrectAnswer2) {
        this.incorrectAnswer2 = incorrectAnswer2;
    }

    public String getIncorrectAnswer3() {
        return incorrectAnswer3;
    }

    public void setIncorrectAnswer3(String incorrectAnswer3) {
        this.incorrectAnswer3 = incorrectAnswer3;
    }
}