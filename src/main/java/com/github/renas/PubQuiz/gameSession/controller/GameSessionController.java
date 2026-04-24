package com.github.renas.PubQuiz.gameSession.controller;

import com.github.renas.PubQuiz.gameSession.GameStatus;
import com.github.renas.PubQuiz.gameSession.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController {

    private final GameSessionService gameSessionService;

    public GameSessionController(GameSessionService gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    @PostMapping("/create-game")
    public String createGame(@RequestBody String name){
        return gameSessionService.createGame(name);
    }
}
