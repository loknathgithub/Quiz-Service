package com.loknath.quiz_service.dao;
import com.loknath.quiz_service.models.UserScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserScoreRepository extends JpaRepository<UserScore, Long> {
    List<UserScore> findAllByClassname(String classname);
}

