package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.QuizRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.QuizResponseDTO;
import org.zeyad.sms.mappers.QuizResponseDTOMapper;
import org.zeyad.sms.mappers.QuizResponseDTOMapper;
import org.zeyad.sms.services.QuizService;
import org.zeyad.sms.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@Setter
@Getter
public class QuizController {
    private QuizService quizService;
    private QuizResponseDTOMapper quizResponseDTOMapper;
    @GetMapping
    public List<QuizResponseDTO> getQuizzes(){
        return quizService.getAll();
    }
    @GetMapping("/{quizId}")
    public QuizResponseDTO getQuizById(@PathVariable Long quizId){
        return quizResponseDTOMapper.map(quizService.getById(quizId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDTO addQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
        return quizService.addQuiz(quizRequestDTO);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Long quizId){
        quizService.deleteById(quizId);
    }

}
