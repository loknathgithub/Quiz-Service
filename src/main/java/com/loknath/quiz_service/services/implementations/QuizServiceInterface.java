package com.loknath.quiz_service.services.implementations;

import java.util.List;

import com.loknath.quiz_service.models.Response;

public interface QuizServiceInterface {
    // public Object createQuiz(String category, int numQ, String title);

    public Object getQuizQuestions(int id);

    public Object calResult(int id, List<Response> responses);
}
