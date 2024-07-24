package com.loknath.quiz_service.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.loknath.quiz_service.dao.QuizRepo;
import com.loknath.quiz_service.feign.QuizInterface;
import com.loknath.quiz_service.models.Quiz;
import com.loknath.quiz_service.models.Response;
import com.loknath.quiz_service.services.implementations.QuizServiceInterface;

@Service
public class QuizService implements QuizServiceInterface{

    @Autowired
    QuizRepo repoQuiz;

    @Autowired
    QuizInterface quizInterface;

    public Object createQuiz(String category, int numQ, String title) {
        
            @SuppressWarnings("unchecked")
            List<Integer> questions= (List<Integer>) quizInterface.getQuiz(category, numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            return repoQuiz.save(quiz);
    }

    public Object getQuizQuestions(int id) {
        Quiz quiz = repoQuiz.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        Object questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public Object calResult(int id, List<Response> responses) {
        
        Object score = quizInterface.getScore(responses);
        return score;
    }


}
