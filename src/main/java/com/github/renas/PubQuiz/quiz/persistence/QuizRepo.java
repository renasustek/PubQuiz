package com.github.renas.PubQuiz.quiz.persistence;

import com.github.renas.PubQuiz.quiz.entities.QuestionDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizRepo extends CrudRepository<QuestionDao, UUID> {
}
