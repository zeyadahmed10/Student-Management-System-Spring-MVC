package org.zeyad.sms.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.dto.request.QuizRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.QuizResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Quiz;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.mappers.EntityMapper;
import org.zeyad.sms.mappers.QuizResponseDTOMapper;
import org.zeyad.sms.repos.QuizRepository;

public class QuizService extends CrudService<Quiz, Long, QuizResponseDTO>{
    private QuizRepository quizRepository;
    private QuizResponseDTOMapper quizResponseDTOMapper;
    @Override
    protected JpaRepository<Quiz, Long> getRepository() {
        return quizRepository;
    }

    @Override
    protected EntityMapper<Quiz, QuizResponseDTO> getMapper() {
        return quizResponseDTOMapper;
    }
    public QuizResponseDTO addQuiz(QuizRequestDTO quizRequestDTO) {
        Quiz quiz = Quiz.builder()
                .title(quizRequestDTO.getTitle())
                .description(quizRequestDTO.getDescription())
                .build();
        return add(quiz);
    }

    public void updateQuizById(Long quizId, QuizRequestDTO quizRequestDTO) {
        Quiz quiz = getById(quizId);
        quiz.setTitle(quizRequestDTO.getTitle());
        quiz.setDescription(quizRequestDTO.getDescription());
        add(quiz);
    }

}
