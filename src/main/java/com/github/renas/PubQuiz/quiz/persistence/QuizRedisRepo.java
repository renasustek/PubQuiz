package com.github.renas.PubQuiz.quiz.persistence;

import com.github.renas.PubQuiz.quiz.Question;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRedisRepo {

    private final RedisTemplate<String, Object> redisTemplate;


    public QuizRedisRepo(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void loadQuestions(String gamePin, List<Question> questions){
        questions.forEach(question -> {
            redisTemplate.opsForList().rightPush("game:"+gamePin+":questions",question);
        });
    }
}
