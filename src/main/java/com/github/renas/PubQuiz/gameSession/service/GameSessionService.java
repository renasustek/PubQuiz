package com.github.renas.PubQuiz.gameSession.service;

import com.github.renas.PubQuiz.gameSession.persistence.GameSessionRedisRepo;
import com.github.renas.PubQuiz.quiz.service.QuizService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameSessionService {

    private final GameSessionRedisRepo gameSessionRedisRepo;
    private final QuizService quizService;
    public GameSessionService(GameSessionRedisRepo gameSessionRedisRepo, QuizService quizService) {
        this.gameSessionRedisRepo = gameSessionRedisRepo;
        this.quizService = quizService;
    }
    private String generatePin(){
        String pin = String.valueOf(new Random().nextInt(9000) + 1000);

        while (gameSessionRedisRepo.pinExists(pin)){
            pin = String.valueOf(new Random().nextInt(9000) + 1000);
        }

        return pin;
    }

    public String createGameLobby(String hostName){
        String pin = generatePin();
        gameSessionRedisRepo.createGameLobby(pin, hostName);
        quizService.loadQuiz(pin);
        return pin;
    }
}
