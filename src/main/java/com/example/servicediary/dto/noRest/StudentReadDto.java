package com.example.servicediary.dto.noRest;

import com.example.servicediary.entity.Mentor;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentReadDto {

    private int id;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    private String family;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    private String name;
    private String level;
    private String email;
    private String role;
    private String password;
}
