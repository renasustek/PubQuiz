package com.github.renas.PubQuiz.quiz.controller;

import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionRequest;
import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionResponse;
import com.github.renas.PubQuiz.quiz.payloads.QuestionResponse;
import com.github.renas.PubQuiz.quiz.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/get-question")
    public QuestionResponse getQuestion(@RequestBody String pin){
        return quizService.getQuestion(pin);
    }

    @PostMapping("/answer-question")
    public AnswerQuestionResponse answerQuestion(@RequestBody AnswerQuestionRequest req){
        return quizService.answerQuestion(req);
    }

    @GetMapping("/next-question")
    public int incrementQuestion(@RequestBody String pin){
        return quizService.incrementQuestionIndex(pin);
    }
}
