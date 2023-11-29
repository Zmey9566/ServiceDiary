package com.example.servicediary;

import com.example.servicediary.dto.noRest.MentorSaveDto;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.mapper.MentorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class MentorMapperTest extends TestBase{

    MentorMapper mentorMapper;

    Long price = 150000L;

    private final Mentor mentor = new Mentor("Aivazovskiy", "Stepan", price);

    @Autowired
    public MentorMapperTest(MentorMapper mentorMapper) {
        this.mentorMapper = mentorMapper;
    }

    @Test
    void mentorToDtoTest() {
        final var mentorReadDto = mentorMapper.mapToMentorDto(mentor);
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
                .build();
        final var mentor1 = mentorMapper.mapToMentor(stepanovDto);

        final var yujhakovDto = MentorSaveDto.builder()
                .family("Yujhakov")
                .name("Petr")
                .price(price)
                .build();
        final var mentor2 = mentorMapper.mapToMentor(yujhakovDto);

        assertAll(
                () -> assertEquals(mentor1.getFamily(), stepanovDto.getFamily(),
                        "Ошибка в фамилии mentor1"),
                () -> assertEquals(mentor1.getName(), stepanovDto.getName(),
                        "Ошибка в имени mentor1"),
                () -> assertEquals(mentor1.getPrice(), stepanovDto.getPrice(),
                        "Ошибка в зарплате mentor1"),
                () -> assertEquals(mentor2.getFamily(), yujhakovDto.getFamily(),
                        "Ошибка в фамилии mentor1"),
                () -> assertEquals(mentor2.getName(), yujhakovDto.getName(),
                        "Ошибка в имени mentor1"),
                () -> assertEquals(mentor2.getPrice(), yujhakovDto.getPrice(),
                        "Ошибка в зарплате mentor1"),
                () -> assertNotEquals(stepanovDto.getFamily(), yujhakovDto.getFamily(),
                        "Фамилии ментров совпадают"),
                () -> assertEquals(stepanovDto.getName(), yujhakovDto.getName(),
                        "Имена ментров совпадают")
        );

    }

}
