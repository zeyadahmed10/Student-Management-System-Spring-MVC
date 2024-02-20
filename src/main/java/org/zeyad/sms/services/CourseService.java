package org.zeyad.sms.services;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.dto.request.CourseRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.entity.Teacher;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.CourseResponseDTOMapper;
import org.zeyad.sms.mappers.EntityMapper;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.repos.CourseRepository;
import org.zeyad.sms.repos.StudentRepository;
import org.zeyad.sms.repos.TeacherRepository;

import java.util.List;
@Setter
@Getter
public class CourseService extends CrudService<Course, Long, CourseResponseDTO>{

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CourseResponseDTOMapper courseResponseDTOMapper;
    private StudentResponseDTOMapper studentResponseDTOMapper;
    private TeacherRepository teacherRepository;
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
        Student student = studentRepository.findByEmail(studentDTO.getEmail()).orElseThrow(()->
                new ResourceNotFoundException("No Student found with email " + studentDTO.getEmail()));
        course.getStudents().add(student);
        add(course);

    }

    public void removeStudentFromCourse(Long courseId, Long studentId) {
        courseRepository.deleteStudentFromCourse(courseId, studentId);
    }


    public void addTeacher(Long courseId, TeacherRequestDTO teacherRequestDTO) {
        Course course = getById(courseId);
        Teacher teacher = teacherRepository.findByEmail(teacherRequestDTO.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("No teacher found with email " + teacherRequestDTO.getEmail()));
        course.setTeacher(teacher);
        add(course);
    }

    public void removeTeacherFromCourse(Long courseId, Long teacherId) {
        Course course = getById(courseId);
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new ResourceNotFoundException("No teacher found with id " + teacherId));
        if(course.getTeacher().getId()!=teacherId)
            return;
        course.setTeacher(null);
        add(course);

    }
}
