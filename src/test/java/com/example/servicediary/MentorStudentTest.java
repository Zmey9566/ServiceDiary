package com.example.servicediary;

import com.example.servicediary.dao.MentorStudentDao;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MentorStudentTest extends TestBase{

    MentorStudentDao mentorStudentDao;
    @Autowired
    public MentorStudentTest(MentorStudentDao mentorStudentDao) {
        this.mentorStudentDao = mentorStudentDao;
    }

    @Test
    @DisplayName("Поиск студентов по mentor_id")
    void testFindStudentsByMentorId() {
        final var mentorById = mentorStudentDao.findByMentorId(1).stream()
                .map(e -> e.getStudent()).collect(Collectors.toList());
        assertAll(
                () -> assertEquals(mentorById.size(), 3),
                () -> assertEquals(mentorById.get(0).getFamily(), "Kazakov",
                        "Ошибка в фамилии студента с индексом 0"),
                () -> assertEquals(mentorById.get(0).getName(), "Ruslan",
                        "Ошибка в имени студента с индексом 0"),
                () -> assertEquals(mentorById.get(1).getFamily(), "Grigoryan",
                        "Ошибка в фамилии студента с индексом 1"),
                () -> assertEquals(mentorById.get(1).getName(), "Artur",
                        "Ошибка в фамилии студента с индексом 1")
        );
    }


}

