package com.github.renas.PubQuiz.user.persistance;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedisRepo {
    private final RedisTemplate<String, Object> redisTemplate;

    public UserRedisRepo(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void appendScore(String pin, String username, int score) {
        Object data = redisTemplate.opsForValue().get("game:"+pin+":users");

    }
}
