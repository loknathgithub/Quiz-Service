package com.loknath.quiz_service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_classes")  // Avoid using "Class" as the table name
public class QuizName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String className;

    // Constructors
    public QuizName() {}

    public QuizName(String className) {
        this.className = className;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
