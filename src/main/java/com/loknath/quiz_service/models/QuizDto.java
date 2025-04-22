package com.loknath.quiz_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private String categoryName;
    private Integer numofQuestions;
    private String title;
    private String className;
}
