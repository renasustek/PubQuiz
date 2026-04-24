package com.github.renas.PubQuiz.quiz;

import java.util.List;
import java.util.UUID;

public record Question(UUID id,
                       String question,
                       String correctAnswer,
                       List<String> incorrectAnswers) {
}
