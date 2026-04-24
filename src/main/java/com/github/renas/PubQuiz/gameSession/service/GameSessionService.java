package com.github.renas.PubQuiz.gameSession.service;

import com.github.renas.PubQuiz.gameSession.persistence.GameSessionRedisRepo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameSessionService {

    private final GameSessionRedisRepo gameSessionRedisRepo;

    public GameSessionService(GameSessionRedisRepo gameSessionRedisRepo) {
        this.gameSessionRedisRepo = gameSessionRedisRepo;
    }
    private String generatePin(){
        String pin = String.valueOf(new Random().nextInt(9000) + 1000);

        while (gameSessionRedisRepo.pinExists(pin)){
            pin = String.valueOf(new Random().nextInt(9000) + 1000);
        }

        return pin;
    }

    public String createGame(String hostName){
        String pin = generatePin();
        gameSessionRedisRepo.createGame(pin, hostName);
        return pin;
    }
}
