package com.github.renas.PubQuiz.user.service;

import com.github.renas.PubQuiz.user.persistance.UserRedisRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRedisRepo userRedisRepo;

    public UserService(UserRedisRepo userRedisRepo) {
        this.userRedisRepo = userRedisRepo;
    }

    public void appendScore(String pin, String name, int score){
        userRedisRepo.appendScore(pin, name,score);
    }

}
