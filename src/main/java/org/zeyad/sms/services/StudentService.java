package org.zeyad.sms.services;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.entity.Teacher;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.repos.StudentRepository;

import java.util.List;

@Setter
public class StudentService {
    private StudentRepository studentRepository;
    public List<StudentResponseDTO> getStudents(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return StudentResponseDTOMapper.map(
                studentRepository.findByNameContainingAndEmailContaining(name, email, pageable)
        );
    }

    public StudentResponseDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException("No student found with id " + studentId));
        return StudentResponseDTOMapper.map(student);
    }

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = Student.builder()
                .name(studentRequestDTO.getName())
                .email(studentRequestDTO.getEmail())
                .build();
        return StudentResponseDTOMapper.map(studentRepository.save(student));
    }

    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void updateStudentById(Long studentId, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->
                new ResourceNotFoundException("No teacher found with id " + studentId));
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        studentRepository.save(student);
    }
}
