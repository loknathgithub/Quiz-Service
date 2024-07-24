package com.loknath.quiz_service.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.loknath.quiz_service.models.QuizDto;
import com.loknath.quiz_service.models.Response;
import com.loknath.quiz_service.services.QuizService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping(value = "/quiz", method=RequestMethod.GET)
public class QuizController {

    QuizService service;

    public QuizController(QuizService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDto quizDto) {
        try {
            return new ResponseEntity<>(service.createQuiz(quizDto.getCategoryName(), quizDto.getNumofQuestions(), quizDto.getTitle()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuizQuestions(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.getQuizQuestions(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: "+e, HttpStatus.INTERNAL_SERVER_ERROR);  
        }
    }
    
    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitResponse(@PathVariable int id, @RequestBody List<Response> responses) {
        try {
            return new ResponseEntity<>(service.calResult(id,responses), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
