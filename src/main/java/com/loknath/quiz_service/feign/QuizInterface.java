package com.loknath.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.loknath.quiz_service.models.Response;

@FeignClient("QUESTION-SERVICES")
public interface QuizInterface {
 @GetMapping("/question/generate")
    public ResponseEntity<?> getQuiz(@RequestParam String categoryName, @RequestParam Integer numQ);

    @PostMapping("/question/getquestions")
    public Object getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/getscore")
    public Object getScore(@RequestBody List<Response> response);
}
