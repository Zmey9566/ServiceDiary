package com.example.servicediary.dto.noRest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentSaveDto {

    String family;
    String name;
    String level;
}
