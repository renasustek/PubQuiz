package com.github.renas.PubQuiz.gameSession.persistence;

import com.github.renas.PubQuiz.gameSession.GameState;
import com.github.renas.PubQuiz.gameSession.GameStatus;
import com.github.renas.PubQuiz.gameSession.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

@Repository
public class GameSessionRedisRepo {

    private final RedisTemplate<String, Object> redisTemplate;

    public GameSessionRedisRepo(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean pinExists(String pin) {
        return redisTemplate.opsForValue().get("game:" + pin) != null;

    }

    public void createGameLobby(String pin, GameState gameState) {
        redisTemplate.opsForValue().set("game:" + pin, gameState);
    }

    public Long joinGame(String pin, User user) {
        return redisTemplate.opsForSet().add("game:" + pin + ":users", user);
    }

    public void startGame(String pin, GameState gameState) {
        redisTemplate.opsForValue().set("game:" + pin, gameState);
    }

    public GameState getGameState(String pin){
        return new ObjectMapper().convertValue(redisTemplate.opsForValue().get("game:" + pin), GameState.class);
    }

    public int incrementQuestionIndex(String pin) {
        GameState oldState = new ObjectMapper().convertValue(redisTemplate.opsForValue().get("game:" + pin), GameState.class);
        GameState newState = new GameState(oldState.status(), oldState.currentQuestionIndex() + 1);
        redisTemplate.opsForValue().set("game:" + pin, newState);
        GameState updatedState = new ObjectMapper().convertValue(redisTemplate.opsForValue().get("game:" + pin), GameState.class);
        return updatedState.currentQuestionIndex();
    }
}


