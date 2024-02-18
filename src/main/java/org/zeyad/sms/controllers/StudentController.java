package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.repos.StudentRepository;

@RestController
@RequestMapping("/api/v1/students")
@Setter
@Getter
public class StudentController {

    private StudentRepository studentRepository;
    @GetMapping
    @ResponseBody
    public Student getStudents(){
        return Student.builder().email("email3").name("name3").build();
    }
}
