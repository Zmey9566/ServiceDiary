package com.example.servicediary.dto.noRest;

import com.example.servicediary.entity.Role;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MentorSaveDto {
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Фамилия")
    @NonNull
    private String family;
    @Size(min = 2, max = 20, message = "Некорректные данные в поле Имя")
    @NonNull
    private String name;
    private Long price;
    private String email;
    private Role roles;
    private String password;
//    private List<StudentSaveDto> students;
}
