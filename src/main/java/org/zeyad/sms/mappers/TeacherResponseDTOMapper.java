package org.zeyad.sms.mappers;

import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherResponseDTOMapper implements EntityMapper<Teacher, TeacherResponseDTO> {
    @Override
    public TeacherResponseDTO map(Teacher teacher){
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .build();
    }
    @Override
    public List<TeacherResponseDTO> map(List<Teacher> teachers){
        List<TeacherResponseDTO> result = new ArrayList<>();
        for(Teacher teacher: teachers){
            result.add(map(teacher));
        }
        return result;
    }
}
