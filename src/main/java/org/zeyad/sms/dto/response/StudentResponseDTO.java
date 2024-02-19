package org.zeyad.sms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
}
