package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.QuizRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.QuizResponseDTO;
import org.zeyad.sms.mappers.QuizResponseDTOMapper;
import org.zeyad.sms.mappers.QuizResponseDTOMapper;
import org.zeyad.sms.services.QuizService;
import org.zeyad.sms.services.StudentService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/quizzes")
@Setter
@Getter
public class QuizController {
    private QuizService quizService;
    private QuizResponseDTOMapper quizResponseDTOMapper;
    @GetMapping
    public String getQuizzes(Model model){
        List<QuizResponseDTO> quizzes = quizService.getAll();
        model.addAttribute("quizzes", quizzes);
        return "index";
    }
    @GetMapping("/{quizId}")
    public String getQuizById(@PathVariable Long quizId, Model model){
        QuizResponseDTO quiz = quizResponseDTOMapper.map(quizService.getById(quizId));
        model.addAttribute("quiz", quiz);
        return "index";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addQuiz(@RequestBody QuizRequestDTO quizRequestDTO, Model model){
        QuizResponseDTO quiz = quizService.addQuiz(quizRequestDTO);
        model.addAttribute("quiz", quiz);
        return "index";
    }

    @DeleteMapping("/{quizId}")
    public String deleteQuiz(@PathVariable Long quizId, Model model){
        quizService.deleteById(quizId);
        model.addAttribute("message", "Quiz deleted successfully");
        return "index";
    }

}
