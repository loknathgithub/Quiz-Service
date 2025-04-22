package com.loknath.quiz_service.dao;

import com.loknath.quiz_service.models.QuestionWrapper;
import com.loknath.quiz_service.models.QuizDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.loknath.quiz_service.models.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
    @Query(value = "SELECT q.id , q.question_title, q.option1, q.option2, q.option3, q.option4 from question as q " +
            "join quiz_classes as qc " +
            "ON qc.id=q.quiz_class_id " +
            "where qc.class_name= :classname", nativeQuery = true)
    List<Object[]> findAllByClassname(@Param("classname") String classname);
}
