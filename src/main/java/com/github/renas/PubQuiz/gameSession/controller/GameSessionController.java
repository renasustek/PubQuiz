package com.github.renas.PubQuiz.gameSession.controller;

import com.github.renas.PubQuiz.gameSession.payloads.requests.EndQuizRequest;
import com.github.renas.PubQuiz.gameSession.payloads.requests.JoinGameRequest;
import com.github.renas.PubQuiz.gameSession.payloads.responses.JoinGameResponse;
import com.github.renas.PubQuiz.gameSession.service.GameSessionService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/join-game")
    public JoinGameResponse joinLobby(@RequestBody JoinGameRequest req){
        return gameSessionService.joinGame(req);
    }

    @GetMapping("/start-game")
    public void startGame(@RequestBody String pin){
        gameSessionService.startGame(pin);
    }

    @GetMapping("/end-quiz")
    public void endGame(@RequestBody EndQuizRequest req){
        gameSessionService.endGame(req);
    }
}
