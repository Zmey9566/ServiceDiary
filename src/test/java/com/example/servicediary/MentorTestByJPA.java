package com.example.servicediary;

import com.example.servicediary.dao.MentorDao;
import com.example.servicediary.entity.Mentor;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MentorTestByJPA extends TestBase {

    MentorDao mentorDao;

    BigDecimal price = new BigDecimal(10_000_000_456.45);
    BigDecimal price2 = new BigDecimal(1500000.34);
    BigDecimal price3 = new BigDecimal(1500000.34567800001241266727447509765625);

    private final Mentor mentor1 = new Mentor("Ivanov", "Ivan", price);
    private final Mentor mentor2 = new Mentor("Petrov", "Ivan", price);
    private final Mentor mentor3 = new Mentor("Sidorov", "Ivan", price);

    @Autowired
    public MentorTestByJPA(MentorDao mentorDao) {
        this.mentorDao = mentorDao;
    }

    @Test
    void getAllTest() {
        assertEquals(mentorDao.findAll().size(), 3);
    }

    @Test
    void getAllByFamilyTest() {
        System.out.println(mentorDao.getAllByFamily("Sidorov", Sort.by(Sort.Direction.DESC, "id")));
    }

    @Test
    void getTest() {
        System.out.println(mentorDao.get("Ivan"));
    }

    @Nested
    @DisplayName("Positive tests for Mentor")
    class PositiveTests {

        @Test
        @DisplayName("Сохранение ментра в БД")
        void saveTest() {
            mentorDao.save(mentor1);
            mentorDao.save(mentor2);
            mentorDao.save(mentor3);

            final var allMentors = mentorDao.findAll();

            final var ivanIvanov = allMentors.stream().filter(m -> m.getId() == 4).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 4 не найден"));
            final var ivanPetrov = allMentors.stream().filter(m -> m.getId() == 5).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 5 не найден"));
            final var ivanSidorov = allMentors.stream().filter(m -> m.getId() == 6).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 6 не найден"));

            assertAll(
                    () -> assertEquals(ivanIvanov.getFamily(), mentor1.getFamily(),
                            "Ошибка в фамилии для ivanIvanov"),
                    () -> assertEquals(ivanIvanov.getName(), mentor1.getName(),
                            "Ошибка в имени для ivanIvanov"),
                    () -> assertEquals(ivanIvanov.getPrice(), mentor1.getPrice(),
                            "Ошибка в стоимости для ivanIvanov"),
                    () -> assertEquals(ivanIvanov.getId(), 4,
                            "Поля id не соответствуют для ivanIvanov"),

                    () -> assertEquals(ivanPetrov.getFamily(), mentor2.getFamily(),
                            "Ошибка в фамилии для ivanPetrov"),
                    () -> assertEquals(ivanPetrov.getName(), mentor2.getName(),
                            "Ошибка в имени для ivanPetrov"),
                    () -> assertEquals(ivanPetrov.getPrice(), mentor2.getPrice(),
                            "Ошибка в стоимости для ivanPetrov"),
                    () -> assertEquals(ivanPetrov.getId(), 5,
                            "Поля id не соответствуют ivanPetrov"),

                    () -> assertEquals(ivanSidorov.getFamily(), mentor3.getFamily(),
                            "Ошибка в фамилии для ivanSidorov"),
                    () -> assertEquals(ivanSidorov.getName(), mentor3.getName(),
                            "Ошибка в имени для ivanSidorov"),
                    () -> assertEquals(ivanSidorov.getPrice(), mentor3.getPrice(),
                            "Ошибка в стоимости для ivanSidorov"),
                    () -> assertEquals(ivanSidorov.getId(), 6,
                            "Поля id не соответствуют ivanSidorov"),

                    () -> assertEquals(allMentors.size(), 6,
                            "количество записей в таблице не соответствует")
            );
        }

        @Test
        @DisplayName("Обновление элемента(ов) таблицы")
        void testUpdate() {
            mentorDao.save(mentor1);
            mentorDao.save(Mentor.builder().family("f").name("f").price(price).build());

            final var allMentors = mentorDao.findAll();
            final var ivanIvanov = allMentors.stream().filter(m -> m.getId() == 4).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 4 не найден"));
            final var mentor2 = allMentors.stream().filter(m -> m.getId() == 5).findFirst().get().getName();

            ivanIvanov.setFamily("Petrov");
            mentorDao.save(ivanIvanov);
            System.out.println(ivanIvanov);

            assertAll(
                    () -> assertEquals(ivanIvanov.getFamily(), "Petrov",
                            "Ошибка в фамилии для ivanIvanov"),
                    () -> assertEquals(ivanIvanov.getName(), "Ivan",
                            "Ошибка в имени для ivanIvanov"),
                    () -> assertEquals(ivanIvanov.getId(), 4,
                            "Поля id не соответствуют"),

                    () -> assertEquals(mentor2, "f",
                            "поле имя для записи с индексом 5 не соответствует f"),

                    () -> assertEquals(5, mentorDao.findAll().size(),
                            "количество записей в таблице не соответствует")
            );
        }

        @Test
        @DisplayName("Поиск элемента по ID")
        void testFindById() {
            final var allMentors = mentorDao.findAll();
            final var sidorovSemen = allMentors.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
            final var almazovGrigory = allMentors.stream().filter(m -> m.getId() == 2).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 2 не найден"));
            final var evmenovAnton = allMentors.stream().filter(m -> m.getId() == 3).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 3 не найден"));

            assertAll(
                    () -> assertEquals(sidorovSemen.getFamily(), "Sidorov",
                            "Ошибка в имени id 1"),
                    () -> assertEquals(sidorovSemen.getName(), "Semen",
                            "Ошибка в фамилии id 1"),
                    () -> assertEquals(sidorovSemen.getPrice(), price3,
                            "Ошибка в цене id 1"),

                    () -> assertEquals(almazovGrigory.getFamily(), "Almazov",
                            "Ошибка в имени id 2"),
                    () -> assertEquals(almazovGrigory.getName(), "Grigory",
                            "Ошибка в фамилии id 2"),
                    () -> assertEquals(almazovGrigory.getPrice(), price3,
                            "Ошибка в цене id 2"),

                    () -> assertEquals(evmenovAnton.getFamily(), "Evmenov",
                            "Ошибка в имени id 3"),
                    () -> assertEquals(evmenovAnton.getName(), "Anton",
                            "Ошибка в фамилии id 3"),
                    () -> assertEquals(evmenovAnton.getPrice(), price3,
                            "Ошибка в цене id 3")
            );
        }

        @Test
        @DisplayName("Получение всего содержимого таблицы")
        void testGetAll() {
            assertEquals(mentorDao.findAll().size(), 3,
                    "Количество записей в таблице не соответствует");
        }

        @Test
        @DisplayName("Удаление элемента по ID")
        void testRemoveById() {
            final var allMentors = mentorDao.findAll();
            final var sidorovSemen = allMentors.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
            final var evmenovAnton = allMentors.stream().filter(m -> m.getId() == 3).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 3 не найден"));

            mentorDao.deleteById(2);

            assertAll(
                    () -> assertEquals(sidorovSemen.getFamily(), "Sidorov",
                            "Ошибка в имени id 1"),
                    () -> assertEquals(sidorovSemen.getName(), "Semen",
                            "Ошибка в фамилии id 1"),
                    () -> assertEquals(sidorovSemen.getPrice(), price3,
                            "Ошибка в цене id 1"),

                    () -> assertEquals(evmenovAnton.getFamily(), "Evmenov",
                            "Ошибка в имени id 3"),
                    () -> assertEquals(evmenovAnton.getName(), "Anton",
                            "Ошибка в фамилии id 3"),
                    () -> assertEquals(evmenovAnton.getPrice(), price3,
                            "Ошибка в цене id 3"),

                    () -> assertEquals(mentorDao.findAll().size(), 2,
                            "количество записей в таблице не соответствует")
            );
        }

        @Test
        @DisplayName("добавляем 10000 ментров")
        void tenThousandAdd() {
            for (int i = 0; i < 10000; i++) {
                mentorDao.save(new Mentor("Ivan", "Ivanov", price));
            }
            assertEquals(mentorDao.findAll().size(), 10003,
                    "количество записей в таблице не соответствует");
        }



        @Test
        @DisplayName("Очистка таблицы")
        void testRemoveAll() {
            mentorDao.deleteAll();

            assertEquals(mentorDao.findAll().size(), 0,
                    "количество записей в таблице не соответствует");
        }
    }
    @Nested
    @DisplayName("Negative tests for MentorDao")
    class NegativeTests {
        @Test
        @DisplayName("Попытка удаления объекта с несуществующим ID")
        void testNegative() throws InterruptedException {
            System.out.println(mentorDao.findById(10));
            mentorDao.deleteById(100);
//            final var exception = assertThrows(IllegalArgumentException.class, () -> mentorDao.deleteById(10),
//                    "Неверный exception");
//
//            assertEquals("attempt to create delete event with null entity", exception.getMessage(),
//                    "Неверное сообщение об ошибке");

        }

//        @Test
//        @DisplayName("Попытка смены id для созданного объекта")
//        void  testNegative2() {
//            final var allMentors = mentorDao.findAll();
//            final var sidorovSemen = allMentors.stream().filter(m -> m.getId() == 1).findFirst()
//                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));
//            sidorovSemen.setId(5);
//            System.out.println(sidorovSemen);
//            final var exception = assertThrows(HibernateException.class,
//                    () -> mentorDao.save(sidorovSemen), "Неверный exception");
//
//            assertEquals("identifier of an instance of com.example.servicediary.entity." +
//                            "Mentor was altered from 5 to 1", exception.getMessage(),
//                    "Неверное сообщение об ошибке");
//        }

        @Test
        @DisplayName("Попытка установки в поле Фамилия значения больше разрешенной длины")
        void  testNegative3() {
            final var allMentors = mentorDao.findAll();
            final var sidorovSemen = allMentors.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));

            sidorovSemen.setFamily("grsd784gtsd74rg/89d4ts/96b4gsdtf6/9hb7g/dtf7");
            final var exception = assertThrows(NonTransientDataAccessException.class,
                    () -> mentorDao.save(sidorovSemen), "Неверный exception");


            assertEquals("could not execute statement [ERROR: value too long for type character varying(25)]" +
                            " [update mentor set family=?,name=?,price=? where id=?]; " +
                            "SQL [update mentor set family=?,name=?,price=? where id=?]", exception.getMessage(),
                    "Неверное сообщение об ошибке");
        }

        @Test
        @DisplayName("Попытка установки в поле Имя значения больше разрешенной длины")
        void  testNegative4() {
            final var allMentors = mentorDao.findAll();
            final var sidorovSemen = allMentors.stream().filter(m -> m.getId() == 1).findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Элемент с индексом 1 не найден"));

            sidorovSemen.setName("grsd784gtsd74rg/89d4ts/96b4gsdtf6/9hb7g/dtf7");
            final var exception = assertThrows(NonTransientDataAccessException.class,
                    () -> mentorDao.save(sidorovSemen), "Неверный exception");

            System.out.println("ОШИБКА: " + exception.getMessage());

            assertEquals("could not execute statement [ERROR: value too long for type character varying(25)]" +
                            " [update mentor set family=?,name=?,price=? where id=?]; " +
                            "SQL [update mentor set family=?,name=?,price=? where id=?]", exception.getMessage(),
                    "Неверное сообщение об ошибке");
        }
    }
}