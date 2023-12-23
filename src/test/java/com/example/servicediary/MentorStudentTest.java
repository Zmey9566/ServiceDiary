package com.example.servicediary;

import com.example.servicediary.dao.MentorStudentDao;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;

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
        System.out.println(mentorStudentDao.findByMentorId(1).stream()
                .map(e -> e.getStudent()).collect(Collectors.toList()));
    }


}

