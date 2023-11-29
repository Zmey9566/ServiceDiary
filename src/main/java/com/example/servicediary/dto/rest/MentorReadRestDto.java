package com.example.servicediary.dto.rest;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
@Data
public class MentorReadRestDto {

    int id;
    String family;
    String name;
    Long price;
}
