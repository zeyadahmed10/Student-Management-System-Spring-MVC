package org.zeyad.sms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseResponseDTO {
    Long id;
    String title;
    String code;
    String teacherName;
    String teacherEmail;
}
