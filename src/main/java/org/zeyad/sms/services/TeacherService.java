package org.zeyad.sms.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zeyad.sms.dto.request.TeacherRequestDTO;
import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.entity.Teacher;
import org.zeyad.sms.exceptions.ResourceNotFoundException;
import org.zeyad.sms.mappers.EntityMapper;
import org.zeyad.sms.mappers.TeacherResponseDTOMapper;
import org.zeyad.sms.repos.TeacherRepository;

import java.util.List;
@Setter
@Getter
public class TeacherService extends CrudService<Teacher, Long, TeacherResponseDTO> {
    private TeacherRepository teacherRepository;
    private TeacherResponseDTOMapper teacherResponseDTOMapper;
    @Override
    protected JpaRepository<Teacher, Long> getRepository() {
        return teacherRepository;
    }

    @Override
    protected EntityMapper<Teacher, TeacherResponseDTO> getMapper() {
        return teacherResponseDTOMapper;
    }
    public List<TeacherResponseDTO> getTeachers(String name, String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return teacherResponseDTOMapper.map(teacherRepository.
                findByNameContainingAndEmailContaining(name, email, pageable));
    }


    public TeacherResponseDTO addTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = Teacher.builder()
                .name(teacherRequestDTO.getName())
                .email(teacherRequestDTO.getEmail())
                .build();
        return add(teacher);
    }

    public void updateTeacherById(Long teacherId, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = getById(teacherId);
        teacher.setName(teacherRequestDTO.getName());
        teacher.setEmail(teacherRequestDTO.getEmail());
        add(teacher);

    }


}
