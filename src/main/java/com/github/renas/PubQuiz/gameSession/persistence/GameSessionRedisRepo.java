package com.github.renas.PubQuiz.gameSession.persistence;

import com.github.renas.PubQuiz.gameSession.GameState;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;

@Repository
public class GameSessionRedisRepo {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String GAME_PREFIX = "game:";
    private static final String USER_PREFIX = "users:";
    private static final String USERS_SET_SUFFIX = ":users";

    public GameSessionRedisRepo(RedisTemplate<String, Object> redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
    }

    public boolean pinExists(String pin) {
        return redisTemplate.opsForValue().get(GAME_PREFIX + pin) != null;

    }

    public void createGameLobby(String pin, GameState gameState) {
        redisTemplate.opsForValue().set(GAME_PREFIX + pin, gameState);
    }

    public Long joinGame(String pin, String name) {
        redisTemplate.opsForValue().set(USER_PREFIX + name + ":" + pin, 0);
        return redisTemplate.opsForSet().add(GAME_PREFIX + pin + USERS_SET_SUFFIX, name);
    }

    public void startGame(String pin, GameState gameState) {
        redisTemplate.opsForValue().set(GAME_PREFIX + pin, gameState);
    }

    public GameState getGameState(String pin) {
        return new ObjectMapper().convertValue(redisTemplate.opsForValue().get(GAME_PREFIX + pin), GameState.class);
    }

    public int incrementQuestionIndex(String pin) {
        GameState oldState = new ObjectMapper().convertValue(redisTemplate.opsForValue().get(GAME_PREFIX + pin), GameState.class);
        GameState newState = new GameState(oldState.status(), oldState.currentQuestionIndex() + 1);
        redisTemplate.opsForValue().set(GAME_PREFIX + pin, newState);
        GameState updatedState = new ObjectMapper().convertValue(redisTemplate.opsForValue().get(GAME_PREFIX + pin), GameState.class);
        return updatedState.currentQuestionIndex();
    }

    public void endGame(String pin) {
        redisTemplate.delete(GAME_PREFIX + pin);
        redisTemplate.opsForSet().members(GAME_PREFIX + pin + USERS_SET_SUFFIX).forEach(name -> {
            redisTemplate.delete(USER_PREFIX + name + ":" + pin);
        });
        redisTemplate.delete(GAME_PREFIX + pin + USERS_SET_SUFFIX);
        redisTemplate.delete(GAME_PREFIX + pin + ":questions");
    }

}


