package org.zeyad.sms.mappers;

import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentResponseDTOMapper {
    public static StudentResponseDTO map(Student student){
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .build();
    }
    public static List<StudentResponseDTO> map(List<Student> students){
        List<StudentResponseDTO> result = new ArrayList<>();
        for(Student student: students){
            result.add(StudentResponseDTOMapper.map(student));
        }
        return result;
    }
}
