package com.example.servicediary;


import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import org.junit.jupiter.api.Test;

import static com.example.servicediary.TestBase.buildSessionFactory;


public class HibernateTest {

    @Test
    void test1(){
        final var sessionFactory = buildSessionFactory();
        final var session = sessionFactory.openSession();
        session.beginTransaction();
        final var mentor = session.get(Mentor.class, 2);
        final var student = session.get(Student.class, 1);
        session.getTransaction().commit();
        session.close();
        System.out.println(mentor);
        System.out.println(student);

    }
}
