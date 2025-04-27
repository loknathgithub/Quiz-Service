package com.loknath.quiz_service.controllers;

import java.util.List;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.loknath.quiz_service.models.QuizDto;
import com.loknath.quiz_service.models.Response;
import com.loknath.quiz_service.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("${frontend.url}")
public class QuizController {

    @Autowired
    QuizService service;

    public QuizController(QuizService service){
        this.service = service;
    }

    @GetMapping("/by-class/{classname}")
    public ResponseEntity<?> getQuizQuestionsByClass(@PathVariable String classname) {
        try {
            return new ResponseEntity<>(service.getQuizQuestionsByClassname(classname), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/submit")
    public ResponseEntity<?> submitResponse(
            @RequestParam String username, // Accept username as a query parameter
            @RequestParam String classname,
            @RequestBody List<Response> responses)
    {
        System.out.println("In controller"+username+classname+responses);
        try {
            return new ResponseEntity<>(service.calResult(username,classname,responses), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getScores/{classname}")
    public ResponseEntity<?> scoreBoardMethod(@PathVariable String classname){
        try {
            return new ResponseEntity<>(service.getScoreBoard(classname), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Message: " + e, HttpStatus.NOT_FOUND);
        }
    }
}