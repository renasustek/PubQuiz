package com.github.renas.PubQuiz.gameSession.controller;

import com.github.renas.PubQuiz.gameSession.service.GameSessionService;
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
    public String createGameLobby(@RequestBody String name){
        return gameSessionService.createGameLobby(name);
    }
}
