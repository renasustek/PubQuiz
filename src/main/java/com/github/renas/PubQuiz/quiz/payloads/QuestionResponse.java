package com.github.renas.PubQuiz.quiz.payloads;

import java.util.List;

public record QuestionResponse(String question, List<String> answers, boolean isLastQuestion) {
}
