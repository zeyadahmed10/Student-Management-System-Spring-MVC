package org.zeyad.sms.mappers;

import org.zeyad.sms.dto.response.TeacherResponseDTO;
import org.zeyad.sms.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherResponseDTOMapper {
    public static TeacherResponseDTO map(Teacher teacher){
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .build();
    }
    public static List<TeacherResponseDTO> map(List<Teacher> teachers){
        List<TeacherResponseDTO> result = new ArrayList<>();
        for(Teacher teacher: teachers){
            result.add(TeacherResponseDTOMapper.map(teacher));
        }
        return result;
    }
}
