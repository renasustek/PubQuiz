package com.github.renas.PubQuiz.gameSession.service;

import com.github.renas.PubQuiz.gameSession.GameState;
import com.github.renas.PubQuiz.gameSession.GameStatus;
import com.github.renas.PubQuiz.gameSession.payloads.requests.EndQuizRequest;
import com.github.renas.PubQuiz.user.UserData;
import com.github.renas.PubQuiz.gameSession.payloads.requests.JoinGameRequest;
import com.github.renas.PubQuiz.gameSession.payloads.responses.JoinGameResponse;
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
        GameState gameState = new GameState(GameStatus.WAITING, 0);
        gameSessionRedisRepo.createGameLobby(pin, gameState);
        quizService.loadQuiz(pin);
        joinGame(new JoinGameRequest(pin, hostName));
        return pin;
    }

    public JoinGameResponse joinGame(JoinGameRequest req){
        if (gameSessionRedisRepo.joinGame(req.pin(), req.name()) == 1){
            return new JoinGameResponse(req.pin(), req.name());
        }else {
            return null;
        }
    }

    public void startGame(String pin){
        GameState updatedState = new GameState(GameStatus.INPROGRESS, 0);
        gameSessionRedisRepo.startGame(pin, updatedState);
    }

    public void endGame(EndQuizRequest req){
        gameSessionRedisRepo.endGame(req.pin());
    }
}
