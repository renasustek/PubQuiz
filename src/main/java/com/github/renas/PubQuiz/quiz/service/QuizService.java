package com.github.renas.PubQuiz.quiz.service;

import com.github.renas.PubQuiz.quiz.persistence.QuizRedisRepo;
import com.github.renas.PubQuiz.quiz.persistence.QuizRepo;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    private final QuizRedisRepo quizRedisRepo;
    private final QuizRepo quizRepo;

    public QuizService(QuizRedisRepo quizRedisRepo, QuizRepo quizRepo) {
        this.quizRedisRepo = quizRedisRepo;
        this.quizRepo = quizRepo;
    }


}
