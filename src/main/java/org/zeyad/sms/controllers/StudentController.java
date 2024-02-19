package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.services.StudentService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/students")
@Setter
@Getter
public class StudentController {
    private StudentService studentService;
    private StudentResponseDTOMapper studentResponseDTOMapper;
    @GetMapping
    public String getStudents(@RequestParam(value="name", defaultValue = "") String name,
                                                @RequestParam(value="email", defaultValue = "") String email,
                                                @RequestParam(value="page", defaultValue = "0") int page,
                                                @RequestParam(value="size", defaultValue = "10")int size,
                                                Model model){
        List<StudentResponseDTO> students =  studentService.getStudents(name, email, page, size);
        model.addAttribute("students", students);
        return "index";
    }
    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable Long studentId, Model model){
        StudentResponseDTO student =  studentResponseDTOMapper.map(studentService.getById(studentId));
        model.addAttribute("student", student);
        return "index";

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addStudent(@RequestBody StudentRequestDTO studentRequestDTO, Model model){
        StudentResponseDTO student =  studentService.addStudent(studentRequestDTO);
        model.addAttribute("student", student);
        return "index";
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable Long studentId, Model model){
        studentService.deleteById(studentId);
        model.addAttribute("message", "Student deleted successfully");
        return "index";
    }
    @PutMapping("/{courseId}")
    public String updateStudent(@PathVariable Long studentId, @RequestBody StudentRequestDTO studentRequestDTO, Model model){
        studentService.updateStudentById(studentId, studentRequestDTO);
        model.addAttribute("message", "Student updated successfully");
        return "index";
    }
}
