package com.example.servicediary.dto.noRest;

import com.example.servicediary.dto.noRest.StudentReadDto;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class MentorReadDto {

    int id;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    String family;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    String name;
    Long price;
    List<StudentReadDto> students;

}
