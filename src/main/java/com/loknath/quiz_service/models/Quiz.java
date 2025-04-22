package com.loknath.quiz_service.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ElementCollection
    List<Integer> questionIds;

    @ManyToOne
    @JoinColumn(name = "quiz_class_id", nullable = false)
    private QuizName quizName;  // Linking quiz to a class

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    public QuizName getQuizName() {
        return quizName;
    }

    public void setQuizName(QuizName quizName) {
        this.quizName = quizName;
    }
}
