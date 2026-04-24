package com.github.renas.PubQuiz.quiz.controller;

import com.github.renas.PubQuiz.quiz.service.QuizService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    private final QuizService quizService;


    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }
}
