package com.github.renas.PubQuiz.quiz.service;

import com.github.renas.PubQuiz.gameSession.persistence.GameSessionRedisRepo;
import com.github.renas.PubQuiz.quiz.Question;
import com.github.renas.PubQuiz.quiz.Results;
import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionRequest;
import com.github.renas.PubQuiz.quiz.payloads.AnswerQuestionResponse;
import com.github.renas.PubQuiz.quiz.payloads.QuestionResponse;
import com.github.renas.PubQuiz.quiz.persistence.QuizRedisRepo;
import com.github.renas.PubQuiz.quiz.persistence.QuizRepo;
import com.github.renas.PubQuiz.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuizService {
    private final QuizRedisRepo quizRedisRepo;
    private final QuizRepo quizRepo;
    private final GameSessionRedisRepo gameSessionRedisRepo;
    private final UserService userService;
    public QuizService(QuizRedisRepo quizRedisRepo, QuizRepo quizRepo, GameSessionRedisRepo gameSessionRedisRepo, UserService userService) {
        this.quizRedisRepo = quizRedisRepo;
        this.quizRepo = quizRepo;
        this.gameSessionRedisRepo = gameSessionRedisRepo;
        this.userService = userService;
    }

    public void loadQuiz(String gamePin) {
        List<Question> questionList = new ArrayList<>();
        quizRepo.findAll().forEach(questionDao -> {
            questionList.add(new Question(
                    questionDao.getId(),
                    questionDao.getQuestion(),
                    questionDao.getCorrectAnswer(),
                    List.of(questionDao.getIncorrectAnswer1(), questionDao.getIncorrectAnswer2(), questionDao.getIncorrectAnswer3())));
        });

        quizRedisRepo.loadQuestions(gamePin, questionList);
        //loads all questions from database into cache
    }

    public QuestionResponse getQuestion(String pin) {
        Question question = quizRedisRepo.getQuestion(pin, gameSessionRedisRepo.getGameState(pin).currentQuestionIndex());
        List<String> answers = new ArrayList<>();
        answers.add(question.correctAnswer());
        answers.addAll(question.incorrectAnswers());
        return new QuestionResponse(question.question(), answers, checkIfLastQuestion(pin));
    }

    public AnswerQuestionResponse answerQuestion(AnswerQuestionRequest req) {
        String pin = req.pin();
        String correctAnswer = quizRedisRepo.getQuestion(pin, gameSessionRedisRepo.getGameState(pin).currentQuestionIndex()).correctAnswer();
        if (Objects.equals(req.answer(), correctAnswer)) {
            userService.appendScore(req.pin(),req.username(), Results.WINNER.getScore());
            return new AnswerQuestionResponse(Results.WINNER);
        } else {
            return new AnswerQuestionResponse(Results.LOSER);
        }
    }

    public int incrementQuestionIndex(String pin) {
        return gameSessionRedisRepo.incrementQuestionIndex(pin);
    }

    private boolean checkIfLastQuestion(String pin) {
        return quizRedisRepo.getQuestion(pin, gameSessionRedisRepo.getGameState(pin).currentQuestionIndex()+1) == null;

    }

}
