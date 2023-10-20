package com.example.servicediary;


import com.example.servicediary.dao.Dao;
import com.example.servicediary.dao.MentorDaoImpl;
import com.example.servicediary.dao.StudentDaoImpl;
import com.example.servicediary.entity.Mentor;
import com.example.servicediary.entity.Student;
import com.example.servicediary.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@Sql("/schema-data.sql")
@SpringBootTest
@Slf4j
@DisplayName("Тестирование дао слоя Student,by ex Abstract")
public class StudentDaoTest {
    static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    static Session session;
    Dao<Integer, Student> studentDao;
    Dao<Integer, Mentor> mentorDao;
    private final Student ivan = new Student("Ivanov", "Ivan","low");
    private final Student petr = new Student("Smirnov", "Petr","middle");
    private final Student alex = new Student("Kotov", "Alex","high");

    @BeforeEach
    void reloadCash() {
        session = sessionFactory.openSession();
        studentDao = new StudentDaoImpl(Student.class, session);
        mentorDao = new MentorDaoImpl(Mentor.class, session);
    }

    @AfterEach
    void closeSession() {
        session.close();
    }

    @Nested
    @DisplayName("Positive tests for StudentDao")
    class PositiveTests {
        @Test
        @DisplayName("Сохранение элементов в таблицу")
        void testSave() {
            studentDao.save(ivan);
            studentDao.save(petr);
            studentDao.save(alex);

            final var allMentors = studentDao.getAll();
            final var ivanBD = allMentors.stream().filter(m -> m.getId() == 4).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 4 не найден"));
            final var petrBD = allMentors.stream().filter(m -> m.getId() == 5).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 5 не найден"));
            final var alexBD = allMentors.stream().filter(m -> m.getId() == 6).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 6 не найден"));

            assertAll(
                    ()-> assertEquals(ivanBD.getFamily(), ivan.getFamily(),
                            "Ошибка в фамилии для ivan"),
                    ()-> assertEquals(petrBD.getFamily(), petr.getFamily(),
                            "Ошибка в фамилии для petr"),
                    ()-> assertEquals(alexBD.getFamily(), alex.getFamily(),
                            "Ошибка в фамилии для alex"),

                    ()-> assertEquals(ivanBD.getName(), ivan.getName(),
                            "Ошибка в имени для ivan"),
                    ()-> assertEquals(petrBD.getName(), petr.getName(),
                            "Ошибка в имени для petr"),
                    ()-> assertEquals(alexBD.getName(), alex.getName(),
                            "Ошибка в имени для alex"),

                    ()-> assertEquals(ivanBD.getLevel(), ivan.getLevel(),
                            "Ошибка в уровне для ivan"),
                    ()-> assertEquals(petrBD.getLevel(), petr.getLevel(),
                            "Ошибка в уровне для petr"),
                    ()-> assertEquals(alexBD.getLevel(), alex.getLevel(),
                            "Ошибка в уровне для alex"),

                    ()-> assertEquals(ivanBD.getId(), 4,
                            "Поля id не соответствуют для ivan"),
                    ()-> assertEquals(petrBD.getId(), 5,
                            "Поля id не соответствуют для petr"),
                    ()-> assertEquals(alexBD.getId(), 6,
                            "Поля id не соответствуют для alex"),

                    ()-> assertEquals(allMentors.size(), 6,
                            "количество записей в таблице не соответствует")
            );
        }

        @Test
        @DisplayName("Обновление элемента(ов) таблицы")
        void testUpdate() {
            studentDao.save(ivan);
            studentDao.update(Student.builder().family("f").name("f").level("low").build());

            final var allStudents = studentDao.getAll();
            final var ivanBD = allStudents.stream().filter(m -> m.getId() == 4).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 4 не найден"));
            final var student2 = allStudents.stream().filter(m -> m.getId() == 5).findFirst().get().getName();

            ivanBD.setFamily("Petrov");
            studentDao.update(ivanBD);
            System.out.println(ivanBD);

            assertAll(
                    ()-> assertEquals(ivanBD.getFamily(), ivan.getFamily(),
                            "Ошибка в фамилии для ivan"),

                    ()-> assertEquals(ivanBD.getName(), ivan.getName(),
                            "Ошибка в имени для ivan"),

                    ()-> assertEquals(ivanBD.getId(), 4,
                            "Поля id не соответствуют"),

                    ()-> assertEquals(student2, "f",
                            "поле имя для записи с индексом 5 не соответствует f"),

                    ()-> assertEquals(5, studentDao.getAll().size(),
                            "количество записей в таблице не соответствует")
            );
        }

        @Test
        @DisplayName("Поиск элемента по ID")
        void testFindById() {
            studentDao.save(ivan);
            studentDao.save(petr);
            studentDao.save(alex);

            assertAll(
                    ()-> assertEquals(studentDao.findById(4), ivan,
                            "Студент ivan не соответствует записи в таблице с id 4"),
                    ()-> assertEquals(studentDao.findById(5), petr,
                            "Студент petr не соответствует записи в таблице с id 5"),
                    ()-> assertEquals(studentDao.findById(6), alex,
                            "Студент alex не соответствует записи в таблице с id 6")
            );
        }

        @Test
        @DisplayName("Получение всего содержимого таблицы")
        void testGetAll() {
            assertEquals(studentDao.getAll().size(), 3,
                    "количество записей в таблице не соответствует");
        }

        @Test
        @DisplayName("Получение всего содержимого таблицы")
        void testGetAll2() {
            System.out.println(studentDao.getAll());
        }
        @Test
        @DisplayName("Получение всего содержимого таблицы")
        void testMentorStudents() {
            final var students1 = mentorDao.findById(1).getStudents().stream().toList();
            final var mentor1 = mentorDao.findById(1);
            final var students2 = mentorDao.findById(2).getStudents().stream().toList();
            final var mentor2 = mentorDao.findById(2);
            final var students3 = mentorDao.findById(3).getStudents().stream().toList();
            final var mentor3 = mentorDao.findById(3);
            final var student1 = mentor1.getStudents().stream().filter(s -> s.getId() == 1).findFirst();


//            System.out.println("Mentor " + mentor1.getId() + ":");
            log.info("Mentor " + mentor1.getId() + ":");
            System.out.println("FIO:" + mentor1.getFamily() + " " + mentor1.getName()
                    + ", Price: " + mentor1.getPrice());
            System.out.println("Students:");
            for (Student s : students1) {
                System.out.println("ID: " + "<<" + s.getId() + ">>"
                        + " FIO: " + s.getFamily() + " " + s.getName()
                        + ", Level: " + s.getLevel());
            }

            System.out.println("Mentor " + mentor2.getId() + ":");
            System.out.println("FIO:" + mentor2.getFamily() + " " + mentor2.getName()
                    + ", Price: " + mentor2.getPrice());
            System.out.println("Students:");
            for (Student s : students2) {
                System.out.println("ID: " + "<<" + s.getId() + ">>"
                        + " FIO: " + s.getFamily() + " " + s.getName()
                        + ", Level: " + s.getLevel());
            }

            System.out.println("Mentor " + mentor3.getId() + ":");
            System.out.println("FIO:" + mentor3.getFamily() + " " + mentor3.getName()
                    + ", Price: " + mentor3.getPrice());
            System.out.println("Students:");
            for (Student s : students3) {
                System.out.println("ID: " + "<<" + s.getId() + ">>"
                        + " FIO: " + s.getFamily() + " " + s.getName()
                        + ", Level: " + s.getLevel());
            }

            assertEquals(student1.get().getFamily(), studentDao.findById(1).getFamily());
        }



        @Test
        @DisplayName("Удаление элемента по ID")
        void testRemoveById() {
            studentDao.removeById(2);

            assertEquals(studentDao.getAll().size(), 2,
                    "количество записей в таблице не соответствует");

            System.out.println(studentDao.getAll());
        }

        @Test
        @DisplayName("добавление ментра с учетом студентов")
        void testAddNew() {
            System.out.println(studentDao.getAll());
        }

        @Test
        @DisplayName("Очистка таблицы")
        void testRemoveAll() {
            studentDao.removeAll();

            assertEquals(studentDao.getAll().size(), 0,
                    "количество записей в таблице не соответствует");
        }
    }

    @Nested
    @DisplayName("Negative tests for StudentDao")
    class NegativeTests{
        @Test
        @DisplayName("Попытка удаления объекта с несуществующим ID")
        void testNegative() throws InterruptedException {
            final var exception = assertThrows(IllegalArgumentException.class, () -> studentDao.removeById(10),
                    "Неверный exception");

            assertEquals("attempt to create delete event with null entity", exception.getMessage(),
                    "Неверное сообщение об ошибке");

        }

        @Test
        @DisplayName("Попытка смены id для созданного объекта")
        void  testNegative2() {
            final var allStudents = studentDao.getAll();
            final var ruslan = allStudents.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
            ruslan.setId(5);
            final var exception = assertThrows(HibernateException.class,
                    () -> studentDao.update(ruslan), "Неверный exception");

            assertEquals("identifier of an instance of com.example.service_diary.entity." +
                            "Student was altered from 5 to 1", exception.getMessage(),
                    "Неверное сообщение об ошибке");
        }

        @Test
        @DisplayName("Попытка установки в поле Фамилия значения больше разрешенной длины")
        void  testNegative3() {
            final var allStudents = studentDao.getAll();
            final var ruslan = allStudents.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));

            ruslan.setFamily("grsd784gtsd74rg/89d4ts/96b4gsdtf6/9hb7g/dtf7");
            final var exception = assertThrows(HibernateException.class,
                    () -> studentDao.update(ruslan), "Неверный exception");

            assertEquals("could not execute statement" +
                            " [ERROR: value too long for type character varying(25)] " +
                            "[update student set family=?,level=?,name=? where id=?]", exception.getMessage(),
                    "Неверное сообщение об ошибке");
        }

        @Test
        @DisplayName("Попытка установки в поле Имя значения больше разрешенной длины")
        void  testNegative4() {
            final var allStudents = studentDao.getAll();
            final var ruslan = allStudents.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));

            ruslan.setName("grsd784gtsd74rg/89d4ts/96b4gsdtf6/9hb7g/dtf7");
            final var exception = assertThrows(HibernateException.class,
                    () -> studentDao.update(ruslan), "Неверный exception");

            System.out.println("ОШИБКА: " + exception.getMessage());

            assertEquals("could not execute statement" +
                            " [ERROR: value too long for type character varying(25)] " +
                            "[update student set family=?,level=?,name=? where id=?]", exception.getMessage(),
                    "Неверное сообщение об ошибке");
        }
    }
}
