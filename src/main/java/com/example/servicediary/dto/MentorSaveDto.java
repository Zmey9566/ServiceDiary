package com.example.servicediary.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class MentorSaveDto {
    String family;
    String name;
    BigDecimal price;
    List<StudentSaveDto> students;
}
