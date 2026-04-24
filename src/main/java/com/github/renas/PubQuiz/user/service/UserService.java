package com.github.renas.PubQuiz.user.service;

import com.github.renas.PubQuiz.quiz.Results;
import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionRequest;
import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionResponse;
import com.github.renas.PubQuiz.user.persistance.UserRedisRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRedisRepo userRedisRepo;

    public UserService(UserRedisRepo userRedisRepo) {
        this.userRedisRepo = userRedisRepo;
    }

    public void appendScore(AnswerQuestionRequest request, int score){
        userRedisRepo.appendScore(request.pin(), request.username(),score);
    }

}
