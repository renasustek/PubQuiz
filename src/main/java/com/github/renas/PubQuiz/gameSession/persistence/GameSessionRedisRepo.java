package com.github.renas.PubQuiz.gameSession.persistence;

import com.github.renas.PubQuiz.gameSession.GameState;
import com.github.renas.PubQuiz.gameSession.GameStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class GameSessionRedisRepo {

    private final RedisTemplate<String, Object> redisTemplate;

    public GameSessionRedisRepo(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean pinExists(String pin){
        return redisTemplate.opsForValue().get(pin) != null;

    }

    public void createGameLobby(String pin) {
        GameState newState = new GameState(GameStatus.WAITING, 0);
        redisTemplate.opsForValue().set("game:"+pin, newState);
//        redisTemplate.expire(pin, 2, TimeUnit.HOURS);
    }

    public Long joinGame(String pin, String name){
        return redisTemplate.opsForSet().add("game:"+pin+":users", name);
    }

    public void startGame(String pin){
        GameState updatedState = new GameState(GameStatus.INPROGRESS, 0);
        redisTemplate.opsForValue().set("game:"+pin, updatedState);
    }

}
