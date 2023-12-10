package com.example.servicediary.dto.noRest;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentSaveDto {

    private String family;
    private String name;
    private String level;
}
