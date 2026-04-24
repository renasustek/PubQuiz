package com.github.renas.PubQuiz.gameSession.persistence;

import com.github.renas.PubQuiz.gameSession.GameState;
import com.github.renas.PubQuiz.gameSession.GameStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class GameSessionPersistence {

    private final RedisTemplate<String, Object> redisTemplate;

    public GameSessionPersistence(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean pinExists(String pin){
        if (redisTemplate.opsForValue().get(pin) == null){
            return false;
        } else {
            return true;
        }
    }

    public void createGame(String pin, String hostName) {
        GameState newState = new GameState(GameStatus.WAITING, 0);
        redisTemplate.opsForValue().set(pin, newState);
        redisTemplate.expire(pin, 2, TimeUnit.HOURS);
    }

}
