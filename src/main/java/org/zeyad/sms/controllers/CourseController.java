package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.CourseRequestDTO;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.CourseResponseDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Course;
import org.zeyad.sms.mappers.CourseResponseDTOMapper;
import org.zeyad.sms.services.CourseService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/courses")
@Setter
@Getter
public class CourseController {

    private CourseService courseService;
    private CourseResponseDTOMapper courseResponseDTOMapper;
    @GetMapping
    public String getCourses(@RequestParam(value="code", defaultValue = "") String code,
                                              @RequestParam(value="title", defaultValue = "") String title,
                                              @RequestParam(value="page", defaultValue = "0") int page,
                                              @RequestParam(value="size", defaultValue = "10")int size,
                                              Model model){
        List<CourseResponseDTO> courses = courseService.getCourses(code, title, page, size);
        model.addAttribute("courses",courses);
        return "index";
    }
    @GetMapping("/{courseId}")
    public String getCourseById(@PathVariable Long courseId, Model model){
        CourseResponseDTO course = courseResponseDTOMapper.map(courseService.getById(courseId));
        model.addAttribute("course",course);
        return "index";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addCourse(@RequestBody CourseRequestDTO courseDTO, Model model){
        CourseResponseDTO course = courseService.addCourse(courseDTO);
        model.addAttribute("course",course);
        return "index";
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourse(@PathVariable Long courseId, Model model){
        courseService.deleteById(courseId);
        model.addAttribute("message", "Course deleted successfully");
        return "index";
    }
    @PutMapping("/{courseId}")
    public String updateCourse(@PathVariable Long courseId, @RequestBody CourseRequestDTO courseRequestDTO, Model model){
        courseService.updateCourseById(courseId, courseRequestDTO);
        model.addAttribute("message", "Course updated successfully");
        return "index";
    }
    //Get Students Enrolled in the course given courseId
    @GetMapping("/{courseId}/students")
    public String getAllStudentsForCourse(@PathVariable Long courseId,
                                                            @RequestParam(value="page", defaultValue = "0") int page,
                                                            @RequestParam(value="size", defaultValue = "10") int size,
                                                            Model model){
        List<StudentResponseDTO> students = courseService.getStudentsForCourse(courseId);
        model.addAttribute("students", students);
        return "index";
    }
    //Enroll student in course by courseId
    @PostMapping("/{courseId}/students")
    public String enrollStudent(@PathVariable Long courseId, StudentRequestDTO studentDTO, Model model){
        courseService.enrollStudent(courseId, studentDTO);
        model.addAttribute("message", "Student enrolled successfully");
        return "index";
    }
    //Remove Student from specific course
    @DeleteMapping("/{courseId}/students/{studentId}")
    public String removeStudent(@PathVariable Long courseId, @PathVariable Long studentId, Model model){
        courseService.removeStudentFromCourse(courseId, studentId);
        model.addAttribute("message", "Student removed from course successfully");
        return "index";
    }
    //add teacher to for specific course
    @PostMapping("/{courseId}/teachers")
    public String addTeacher(@PathVariable Long courseId, TeacherRequestDTO teacherRequestDTO, Model model){
        courseService.addTeacher(courseId, teacherRequestDTO);
        model.addAttribute("message", "Teacher added for course successfully");
        return "index";

    }
    //Remove Student from specific course
    @DeleteMapping("/{courseId}/teachers/{teacherId}")
    public String removeTeacher(@PathVariable Long courseId, @PathVariable Long teacherId, Model model){
        courseService.removeTeacherFromCourse(courseId, teacherId);
        model.addAttribute("message", "Teacher removed from course successfully");
        return "index";
    }

}
