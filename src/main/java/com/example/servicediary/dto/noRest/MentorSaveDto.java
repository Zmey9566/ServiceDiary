package com.example.servicediary.dto.noRest;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MentorSaveDto {
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    String family;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    String name;
    Long price;
    List<StudentSaveDto> students;
}
