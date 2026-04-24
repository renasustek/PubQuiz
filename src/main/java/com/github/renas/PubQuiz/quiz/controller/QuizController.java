package com.github.renas.PubQuiz.quiz.controller;

import com.github.renas.PubQuiz.quiz.Question;
import com.github.renas.PubQuiz.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/get-question")
    public Question getQuestion(@RequestBody String pin){
        return quizService.getQuestion(pin);
    }

}
