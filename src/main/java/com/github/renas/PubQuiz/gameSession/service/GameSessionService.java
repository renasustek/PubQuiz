package com.github.renas.PubQuiz.gameSession.service;

import com.github.renas.PubQuiz.gameSession.persistence.GameSessionPersistence;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GameSessionService {

    private final GameSessionPersistence gameSessionPersistence;

    public GameSessionService(GameSessionPersistence gameSessionPersistence) {
        this.gameSessionPersistence = gameSessionPersistence;
    }
    private String generatePin(){
        String pin = String.valueOf(new Random().nextInt(9000) + 1000);

        while (gameSessionPersistence.pinExists(pin)){
            pin = String.valueOf(new Random().nextInt(9000) + 1000);
        }

        return pin;
    }

    public String createGame(String hostName){
        String pin = generatePin();
        gameSessionPersistence.createGame(pin, hostName);
        return pin;
    }
}
