package org.zeyad.sms.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zeyad.sms.dto.request.CourseRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.CourseResponseDTOMapper;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.repos.CourseRepository;
import org.zeyad.sms.repos.StudentRepository;

import java.util.List;
@Getter
@Setter
public class CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    public List<CourseResponseDTO> getCourses(String code, String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return CourseResponseDTOMapper.map(
                courseRepository.findByCodeContainingAndTitleContaining(code, title, pageable));
    }

    public CourseResponseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new ResourceNotFoundException("No course found with id " + courseId));
        return CourseResponseDTOMapper.map(course);
    }

    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
        Course course = Course.builder()
                .code(courseRequestDTO.getCode())
                .title(courseRequestDTO.getTitle())
                .build();
        return CourseResponseDTOMapper.map(courseRepository.save(course));
    }

    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void updateCourseById(Long courseId, CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new ResourceNotFoundException("No course found with id " + courseId));
        course.setCode(courseRequestDTO.getCode());
        course.setTitle(courseRequestDTO.getTitle());
        courseRepository.save(course);
    }

    public List<StudentResponseDTO> getStudentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new ResourceNotFoundException("No course found with id " + courseId));
        return StudentResponseDTOMapper.map(course.getStudents());
    }

    public void enrollStudent(Long courseId, StudentRequestDTO studentDTO) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new ResourceNotFoundException("No course found with id " + courseId));
        Student student = studentRepository.fidByEmail(studentDTO.getEmail()).orElseThrow(()->
                new ResourceNotFoundException("No Student found with email " + studentDTO.getEmail()));
        course.getStudents().add(student);
        courseRepository.save(course);

    }

    public void removeStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()->
                new ResourceNotFoundException("No course found with id " + courseId));
    }
}
