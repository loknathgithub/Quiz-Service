package com.loknath.quiz_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loknath.quiz_service.models.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {

}
