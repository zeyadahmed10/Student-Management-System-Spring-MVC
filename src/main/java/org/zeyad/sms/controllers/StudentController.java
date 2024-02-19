package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@Setter
@Getter
public class StudentController {
    private StudentService studentService;
    private StudentResponseDTOMapper studentResponseDTOMapper;
    @GetMapping
    public List<StudentResponseDTO> getStudents(@RequestParam(value="name", defaultValue = "") String name,
                                                @RequestParam(value="email", defaultValue = "") String email,
                                                @RequestParam(value="page", defaultValue = "0") int page,
                                                @RequestParam(value="size", defaultValue = "10")int size){
        return studentService.getStudents(name, email, page, size);
    }
    @GetMapping("/{studentId}")
    public StudentResponseDTO getStudentById(@PathVariable Long studentId){
        return studentResponseDTOMapper.map(studentService.getById(studentId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent(@RequestBody StudentRequestDTO studentRequestDTO){
        return studentService.addStudent(studentRequestDTO);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteById(studentId);
    }
    @PutMapping("/{courseId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody StudentRequestDTO studentRequestDTO){
        studentService.updateStudentById(studentId, studentRequestDTO);
    }
}
