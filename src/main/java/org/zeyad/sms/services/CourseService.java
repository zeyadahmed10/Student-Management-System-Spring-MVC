package org.zeyad.sms.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.dto.request.CourseRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.CourseResponseDTOMapper;
import org.zeyad.sms.mappers.EntityMapper;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.repos.CourseRepository;
import org.zeyad.sms.repos.StudentRepository;

import java.util.List;
@Setter
public class CourseService extends CrudService<Course, Long, CourseResponseDTO>{

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CourseResponseDTOMapper courseResponseDTOMapper;
    private StudentResponseDTOMapper studentResponseDTOMapper;
    @Override
    protected JpaRepository<Course, Long> getRepository() {
        return this.courseRepository;
    }

    @Override
    protected EntityMapper<Course, CourseResponseDTO> getMapper() {
        return this.courseResponseDTOMapper;
    }

    public List<CourseResponseDTO> getCourses(String code, String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseResponseDTOMapper.map(
                courseRepository.findByCodeContainingAndTitleContaining(code, title, pageable));
    }

    public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO) {
        Course course = Course.builder()
                .code(courseRequestDTO.getCode())
                .title(courseRequestDTO.getTitle())
                .build();
        return add(course);
    }


    public void updateCourseById(Long courseId, CourseRequestDTO courseRequestDTO) {
        Course course = getById(courseId);
        course.setCode(courseRequestDTO.getCode());
        course.setTitle(courseRequestDTO.getTitle());
        add(course);
    }

    public List<StudentResponseDTO> getStudentsForCourse(Long courseId) {
        Course course = getById(courseId);
        return studentResponseDTOMapper.map(course.getStudents());
    }

    public void enrollStudent(Long courseId, StudentRequestDTO studentDTO) {
        Course course = getById(courseId);
        Student student = studentRepository.fidByEmail(studentDTO.getEmail()).orElseThrow(()->
                new ResourceNotFoundException("No Student found with email " + studentDTO.getEmail()));
        course.getStudents().add(student);
        add(course);

    }

    public void removeStudentFromCourse(Long courseId, Long studentId) {
        courseRepository.deleteStudentFromCourse(courseId, studentId);
    }


}
