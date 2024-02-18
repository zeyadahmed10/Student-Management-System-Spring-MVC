package org.zeyad.sms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.repos.StudentRepository;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @GetMapping
    public String getStudents(){
        studentRepository.save(Student.builder().email("email").name("name").build());
        return "Student";
    }
}
