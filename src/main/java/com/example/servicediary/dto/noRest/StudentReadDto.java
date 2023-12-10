package com.example.servicediary.dto.noRest;

import com.example.servicediary.entity.Mentor;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentReadDto {

    private int id;
    private String family;
    private String name;
    private String level;
}
