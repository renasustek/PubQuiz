package com.github.renas.PubQuiz.quiz.service;

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

    public QuizService(QuizRedisRepo quizRedisRepo, QuizRepo quizRepo) {
        this.quizRedisRepo = quizRedisRepo;
        this.quizRepo = quizRepo;
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


}
