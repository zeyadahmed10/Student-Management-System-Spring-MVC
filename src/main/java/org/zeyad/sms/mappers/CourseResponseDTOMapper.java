package org.zeyad.sms.mappers;

import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseResponseDTOMapper implements EntityMapper<Course, CourseResponseDTO> {
    @Override
    public CourseResponseDTO map(Course course){
        String teacherEmail = (course.getTeacher()==null)? "" : course.getTeacher().getEmail();
        String teacherName = (course.getTeacher()==null)? "" : course.getTeacher().getName();
        return CourseResponseDTO.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .teacherName(teacherName)
                .teacherEmail(teacherEmail)
                .build();
    }
    @Override
    public List<CourseResponseDTO> map(List<Course> courses){
        List<CourseResponseDTO> result = new ArrayList<>();
        for(Course course: courses){
            result.add(map(course));
        }
        return result;
    }

}
