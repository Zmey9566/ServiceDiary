package com.example.servicediary;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest
@PropertySource("classpath:application.properties")
public class ReadPropertiesOnSpringTest extends TestBase{

    @Value("${mentor.admin.user-name}")
    String admin;
    @Test
    void configOnSpringTest() {
        Assertions.assertEquals(admin, "admin");
    }
}
