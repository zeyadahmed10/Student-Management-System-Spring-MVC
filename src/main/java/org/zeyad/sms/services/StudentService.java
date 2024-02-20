package org.zeyad.sms.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.dto.request.StudentRequestDTO;
import org.zeyad.sms.dto.response.StudentResponseDTO;
import org.zeyad.sms.entity.Student;
import org.zeyad.sms.entity.Teacher;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.EntityMapper;
import org.zeyad.sms.mappers.StudentResponseDTOMapper;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.repos.StudentRepository;

import java.util.List;

@Setter
@Getter
public class StudentService extends CrudService<Student, Long, StudentResponseDTO>{

    private StudentRepository studentRepository;
    private StudentResponseDTOMapper studentResponseDTOMapper;
    @Override
    protected JpaRepository<Student, Long> getRepository() {
        return studentRepository;
    }

    @Override
    protected EntityMapper<Student, StudentResponseDTO> getMapper() {
        return null;
    }
    public List<StudentResponseDTO> getStudents(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentResponseDTOMapper.map(
                studentRepository.findByNameContainingAndEmailContaining(name, email, pageable)
        );
    }

    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO) {
        Student student = Student.builder()
                .name(studentRequestDTO.getName())
                .email(studentRequestDTO.getEmail())
                .build();
        return add(student);
    }

    public void updateStudentById(Long studentId, StudentRequestDTO studentRequestDTO) {
        Student student = getById(studentId);
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        add(student);
    }


}
