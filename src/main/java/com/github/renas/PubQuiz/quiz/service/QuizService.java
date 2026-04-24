package com.github.renas.PubQuiz.quiz.service;

import com.github.renas.PubQuiz.gameSession.persistence.GameSessionRedisRepo;
import com.github.renas.PubQuiz.quiz.Question;
import com.github.renas.PubQuiz.quiz.persistence.QuizRedisRepo;
import com.github.renas.PubQuiz.quiz.persistence.QuizRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {
    private final QuizRedisRepo quizRedisRepo;
    private final QuizRepo quizRepo;
    private final GameSessionRedisRepo gameSessionRedisRepo;

    public QuizService(QuizRedisRepo quizRedisRepo, QuizRepo quizRepo, GameSessionRedisRepo gameSessionRedisRepo) {
        this.quizRedisRepo = quizRedisRepo;
        this.quizRepo = quizRepo;
        this.gameSessionRedisRepo = gameSessionRedisRepo;
    }

    public void loadQuiz(String gamePin){
        List<Question> questionList = new ArrayList<>();
        quizRepo.findAll().forEach(questionDao -> {
            questionList.add(new Question(
                    questionDao.getId(),
                    questionDao.getQuestion(),
                    questionDao.getCorrectAnswer(),
                    List.of(questionDao.getIncorrectAnswer1(), questionDao.getIncorrectAnswer2(), questionDao.getIncorrectAnswer3())));
        });

        quizRedisRepo.loadQuestions(gamePin, questionList);
        //loads all questions in repo into redis
    }

    public Question getQuestion(String pin){
        return quizRedisRepo.getQuestion(pin);
    }

}
