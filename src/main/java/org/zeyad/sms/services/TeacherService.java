package org.zeyad.sms.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.entity.Teacher;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.repos.TeacherRepository;

import java.util.List;

public class TeacherService {
    private TeacherRepository teacherRepository;
    public List<TeacherResponseDTO> getTeachers(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return TeacherResponseDTOMapper.map(teacherRepository.
                findByNameContainingAndEmailContaining(name, email, pageable));
    }

    public TeacherResponseDTO getByTeacherId(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()->
                new ResourceNotFoundException("No teacher found with id " + teacherId));
        return TeacherResponseDTOMapper.map(teacher);
    }

    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = Teacher.builder()
                .name(teacherRequestDTO.getName())
                .email(teacherRequestDTO.getEmail())
                .build();
        return TeacherResponseDTOMapper.map(teacherRepository.save(teacher));
    }

    public void deleteTeacherById(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    public void updateTeacherById(Long teacherId, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()->
                new ResourceNotFoundException("No teacher found with id " + teacherId));
        teacher.setName(teacherRequestDTO.getName());
        teacher.setEmail(teacherRequestDTO.getEmail());
        teacherRepository.save(teacher);

    }
}
