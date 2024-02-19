package org.zeyad.sms.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String teacherName;
    private String teacherEmail;
}
