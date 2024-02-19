package org.zeyad.sms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherResponseDTO {
    private Long id;
    private String name;
    private String email;
}
