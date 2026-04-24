package com.github.renas.PubQuiz.quiz.persistence;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuizRedisRepo {

    private final RedisTemplate<String, Object> redisTemplate;


    public QuizRedisRepo(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
