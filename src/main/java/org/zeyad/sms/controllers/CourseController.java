package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.CourseRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.mappers.CourseResponseDTOMapper;
import org.zeyad.sms.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@Setter
@Getter
public class CourseController {

    private CourseService courseService;
    private CourseResponseDTOMapper courseResponseDTOMapper;
    @GetMapping
    public List<CourseResponseDTO> getCourses(@RequestParam(value="code", defaultValue = "") String code,
                                              @RequestParam(value="title", defaultValue = "") String title,
                                              @RequestParam(value="page", defaultValue = "0") int page,
                                              @RequestParam(value="size", defaultValue = "10")int size){
        return courseService.getCourses(code, title, page, size);
    }
    @GetMapping("/{courseId}")
    public CourseResponseDTO getCourseById(@PathVariable Long courseId){
        return courseResponseDTOMapper.map(courseService.getById(courseId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDTO addCourse(@RequestBody CourseRequestDTO courseDTO){
        return courseService.addCourse(courseDTO);
    }

    @DeleteMapping("/{courseId}")
    public void deleteCourse(@PathVariable Long courseId){
        courseService.deleteById(courseId);
    }
    @PutMapping("/{courseId}")
    public void updateCourse(@PathVariable Long courseId, @RequestBody CourseRequestDTO courseRequestDTO){
        courseService.updateCourseById(courseId, courseRequestDTO);
    }
    //Get Students Enrolled in the course given courseId
    @GetMapping("/{courseId}/students")
    public List<StudentResponseDTO> getAllStudentsForCourse(@PathVariable Long courseId,
                                                            @RequestParam(value="page", defaultValue = "0") int page,
                                                            @RequestParam(value="size", defaultValue = "10") int size){
        return courseService.getStudentsForCourse(courseId);
    }
    //Enroll student in course by courseId
    @PostMapping("/{courseId}/students")
    public void enrollStudent(@PathVariable Long courseId, StudentRequestDTO studentDTO){
        courseService.enrollStudent(courseId, studentDTO);

    }
    //Remove Student from specific course
    @DeleteMapping("/{courseId}/students/{studentId}")
    public void removeStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        courseService.removeStudentFromCourse(courseId, studentId);
    }
    //add teacher to for specific course
    @PostMapping("/{courseId}/teachers")
    public void addTeacher(@PathVariable Long courseId, TeacherRequestDTO teacherRequestDTO){
        courseService.addTeacher(courseId, teacherRequestDTO);

    }
    //Remove Student from specific course
    @DeleteMapping("/{courseId}/teachers/{teacherId}")
    public void removeTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        courseService.removeTeacherFromCourse(courseId, teacherId);
    }

}
