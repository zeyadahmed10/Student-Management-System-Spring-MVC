package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.services.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/api/v1/teachers")
@Setter
@Getter
public class TeacherController {
    private TeacherService teacherService;
    private TeacherResponseDTOMapper teacherResponseDTOMapper;
    @GetMapping
    public String getTeachers(@RequestParam(value="name", defaultValue = "") String name,
                                                @RequestParam(value="email", defaultValue = "") String email,
                                                @RequestParam(value="page", defaultValue = "0") int page,
                                                @RequestParam(value="size", defaultValue = "10")int size,
                                                Model model){
        List<TeacherResponseDTO> teachers= teacherService.getTeachers(name, email, page, size);
        model.addAttribute("teachers", teachers);
        return "index";
    }
    @GetMapping("/{teacherId}")
    public String getTeacherById(@PathVariable Long teacherId, Model model){
        TeacherResponseDTO teacher =  teacherResponseDTOMapper.map(teacherService.getById(teacherId));
        model.addAttribute("teacher", teacher);
        return "index";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO, Model model){
        TeacherResponseDTO teacher =  teacherService.addTeacher(teacherRequestDTO);
        model.addAttribute("teacher", teacher);
        return "index";
    }

    @DeleteMapping("/{teacherId}")
    public String deleteTeacher(@PathVariable Long teacherId, Model model){
        teacherService.deleteById(teacherId);
        model.addAttribute("message", "Teacher deleted successfully");
        return "index";
    }
    @PutMapping("/{courseId}")
    public String updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherRequestDTO teacherRequestDTO, Model model){
        teacherService.updateTeacherById(teacherId, teacherRequestDTO);
        model.addAttribute("message", "Student updated successfully");
        return "index";
    }
}
