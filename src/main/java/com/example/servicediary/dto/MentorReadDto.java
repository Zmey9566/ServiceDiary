package com.example.servicediary.dto;

import com.example.servicediary.entity.Student;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class MentorReadDto {

    int id;
    String family;
    String name;
    BigDecimal price;
    List<StudentReadDto> students;

}
