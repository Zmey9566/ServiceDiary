package com.example.servicediary.dto.noRest;

import com.example.servicediary.entity.Mentor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentReadDto {

    int id;
    String family;
    String name;
    String level;
}
