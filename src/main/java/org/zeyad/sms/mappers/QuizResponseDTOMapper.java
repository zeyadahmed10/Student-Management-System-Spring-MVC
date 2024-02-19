package org.zeyad.sms.mappers;

import org.zeyad.sms.dto.response.QuizResponseDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizResponseDTOMapper implements EntityMapper<Quiz, QuizResponseDTO> {
    @Override
    public QuizResponseDTO map(Quiz quiz) {
        String teacherEmail = (quiz.getTeacher()==null)? "" : quiz.getTeacher().getEmail();
        String teacherName = (quiz.getTeacher()==null)? "" : quiz.getTeacher().getName();
        return QuizResponseDTO.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .teacherName(teacherName)
                .teacherEmail(teacherEmail)
                .build();
    }

    @Override
    public List<QuizResponseDTO> map(List<Quiz> quizzes) {
        List<QuizResponseDTO> result = new ArrayList<QuizResponseDTO>();
        for(Quiz quiz: quizzes){
            result.add(map(quiz));
        }
        return result;
    }
}
