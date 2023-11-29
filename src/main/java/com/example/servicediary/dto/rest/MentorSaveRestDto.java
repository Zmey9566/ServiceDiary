package com.example.servicediary.dto.rest;

import lombok.*;

@Value
@Builder
@Data
public class MentorSaveRestDto {

    String family;
    String name;
    Long price;
}
