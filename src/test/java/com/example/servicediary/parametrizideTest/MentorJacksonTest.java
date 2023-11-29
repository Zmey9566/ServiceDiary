package com.example.servicediary.parametrizideTest;

import com.example.servicediary.JsonHelper;
import com.example.servicediary.models.People;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class MentorJacksonTest {

    @ParameterizedTest
//    @CsvSource({"Артур, Арутюнов, 21", "Семен, Ризванов, 26", "Петр, Евсюков, 19"})
    @CsvFileSource(resources = "/people.csv", delimiter = '$')
    void getTest(String name, String family, int age) {
        System.out.println(name + ' ' + family + ' ' + age);
    }

    private static Stream<Arguments> peopleX() {
        return Stream.of(
          Arguments.of(new People("Alex", "Ivanov", 15)),
          Arguments.of(new People("Semenov", "Semen", 24)),
          Arguments.of(new People("Petrov", "Petr", 27))
        );
    }

    @ParameterizedTest
    @MethodSource("peopleX")
    void newPeople(People people) {
        System.out.println(people);
    }

    @Test
    public void saveJacksonTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Ситываем Json и печатаем объект
//        1 вариант
//        File file = new File("src/test/resources/people.json");
//        final var people = objectMapper.readValue(file, People.class);
//        System.out.println(people);

//        2 вариант
        System.out.println(JsonHelper.fromJson("src/test/resources/people.json", People.class));

        //Считываем объект и печатаем Json

//        1 Вариант
        People people1 = new People("Виктор", "Зайцев", 51);
//        System.out.println(objectMapper.writeValueAsString(people1));

//        2 вариант

        System.out.println(JsonHelper.toJson(people1));
    }

}
