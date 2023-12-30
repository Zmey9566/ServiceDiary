package com.example.servicediary;

import com.example.servicediary.dao.StudentDao;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Epic(value = "Тестирование БД")
@Story(value = "Тестирование сущности студент")
@SpringBootTest
public class StudentByJPATest extends TestBase{

    StudentDao studentDao;

    @Autowired
    public StudentByJPATest(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Test
    @DisplayName("Поиск студента по email")
    void testFindByEmail() {

        assertEquals(studentDao.findByEmail("Kazakov@mail.ru").getFamily(), "Kazakov",
                "Фамилия студента неверна");
    }
}
