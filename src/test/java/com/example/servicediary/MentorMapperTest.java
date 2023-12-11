package com.example.servicediary;

import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;

import com.example.servicediary.util.MapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class MentorMapperTest extends TestBase{

    MapperUtils mentorMapper;

    Long price = 150000L;

    private final Mentor mentor = new Mentor("Aivazovskiy", "Stepan", price,
            "Aivazovsiy@mail.ru", "ROLE_MENTOR");

    @Autowired
    public MentorMapperTest(MapperUtils mentorMapper) {
        this.mentorMapper = mentorMapper;
    }

    @Test
    void mentorToDtoTest() {
        final var mentorReadDto = mentorMapper.mapToMentorReadDto(mentor);
        assertAll(
                () -> assertEquals(mentorReadDto.getFamily(), mentor.getFamily()),
                () -> assertEquals(mentorReadDto.getName(), mentor.getName()),
                () -> assertEquals(mentorReadDto.getPrice(), mentor.getPrice())
        );
    }

    @Test
    void dtoToMentorTest() {
        final var stepanovDto = MentorSaveDto.builder()
                .family("Stepanov")
                .name("Petr")
                .price(price)
                .email("Stepanov@mail.ru")
                .role("ROLE_MENTOR")
                .password("qwerty11")
                .build();
        final var mentor1 = mentorMapper.mapToMentorSave(stepanovDto);

        final var yujhakovDto = MentorSaveDto.builder()
                .family("Yujhakov")
                .name("Petr")
                .price(price)
                .email("Yujhakov@mail.ru")
                .role("ROLE_MENTOR")
                .password("qwerty12")
                .build();
        final var mentor2 = mentorMapper.mapToMentorSave(yujhakovDto);

        assertAll(
                () -> assertEquals(mentor1.getFamily(), stepanovDto.getFamily(),
                        "Ошибка в фамилии mentor1"),
                () -> assertEquals(mentor1.getName(), stepanovDto.getName(),
                        "Ошибка в имени mentor1"),
                () -> assertEquals(mentor1.getPrice(), stepanovDto.getPrice(),
                        "Ошибка в зарплате mentor1"),
                () -> assertEquals(mentor1.getEmail(), stepanovDto.getEmail(),
                        "Ошибка в email mentor1"),
                () -> assertEquals(mentor1.getRole(), stepanovDto.getRole(),
                        "Ошибка в Role mentor1"),
                () -> assertEquals(mentor2.getFamily(), yujhakovDto.getFamily(),
                        "Ошибка в фамилии mentor1"),
                () -> assertEquals(mentor2.getName(), yujhakovDto.getName(),
                        "Ошибка в имени mentor1"),
                () -> assertEquals(mentor2.getPrice(), yujhakovDto.getPrice(),
                        "Ошибка в зарплате mentor1"),
                () -> assertEquals(mentor2.getEmail(), yujhakovDto.getEmail(),
                        "Ошибка в email mentor1"),
                () -> assertEquals(mentor2.getRole(), yujhakovDto.getRole(),
                        "Ошибка в role mentor1"),
                () -> assertNotEquals(stepanovDto.getFamily(), yujhakovDto.getFamily(),
                        "Фамилии ментров совпадают"),
                () -> assertEquals(stepanovDto.getName(), yujhakovDto.getName(),
                        "Имена ментров совпадают")
        );

    }

}
