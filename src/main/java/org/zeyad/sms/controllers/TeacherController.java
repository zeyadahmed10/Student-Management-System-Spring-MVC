package org.zeyad.sms.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.services.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@Setter
@Getter
public class TeacherController {
    private TeacherService teacherService;
    private TeacherResponseDTOMapper teacherResponseDTOMapper;
    @GetMapping
    public List<TeacherResponseDTO> getTeachers(@RequestParam(value="name", defaultValue = "") String name,
                                                @RequestParam(value="email", defaultValue = "") String email,
                                                @RequestParam(value="page", defaultValue = "0") int page,
                                                @RequestParam(value="size", defaultValue = "10")int size){
        return teacherService.getTeachers(name, email, page, size);
    }
    @GetMapping("/{teacherId}")
    public TeacherResponseDTO getTeacherById(@PathVariable Long teacherId){
        return teacherResponseDTOMapper.map(teacherService.getById(teacherId));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponseDTO addTeacher(@RequestBody TeacherRequestDTO teacherRequestDTO){
        return teacherService.addTeacher(teacherRequestDTO);
    }

    @DeleteMapping("/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteById(teacherId);
    }
    @PutMapping("/{courseId}")
    public void updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherRequestDTO teacherRequestDTO){
        teacherService.updateTeacherById(teacherId, teacherRequestDTO);
    }
}
