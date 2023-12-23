package com.example.servicediary.dto.noRest;

import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentSaveDto {
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
