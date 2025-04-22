package com.loknath.quiz_service.services;
import java.util.List;

import com.loknath.quiz_service.dao.UserScoreRepository;
import com.loknath.quiz_service.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loknath.quiz_service.dao.QuizRepo;
import com.loknath.quiz_service.feign.QuizInterface;
import com.loknath.quiz_service.services.implementations.QuizServiceInterface;

@Service
public class QuizService implements QuizServiceInterface{

    @Autowired
    QuizRepo repoQuiz;

    @Autowired
    UserScoreRepository userRepo;

    @Autowired
    QuizInterface quizInterface;

    @Autowired
    UserScoreRepository userScoreRepository;

    public Object createQuiz(String category, int numQ, String title) {
        
            @SuppressWarnings("unchecked")
            List<Integer> questions= (List<Integer>) quizInterface.getQuiz(category, numQ).getBody();

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestionIds(questions);
            return repoQuiz.save(quiz);
    }

    @Override
    public Object getQuizQuestions(int id) {
        Quiz quiz = repoQuiz.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);
    }

    @Override
    public Object calResult(String username,String classname, List<Response> responses) {

        System.out.println("In service"+username+classname+responses);
        int score = (int) quizInterface.getScore(responses);
        return userScoreRepository.save(new UserScore(classname, username, score));
    }

    @Override
    public List<QuestionWrapper> getQuizQuestionsByClassname(String classname) {

            List<Object[]> results = repoQuiz.findAllByClassname(classname);
            return results.stream()
                    .map(obj -> new QuestionWrapper(
                            (int) obj[0],
                            (String) obj[1],
                            (String) obj[2],
                            (String) obj[3],
                            (String) obj[4],
                            (String) obj[5]))
                    .toList();

    }

    @Override
    public Object getScoreBoard(String classname) {
        return userRepo.findAllByClassname(classname);
    }
}