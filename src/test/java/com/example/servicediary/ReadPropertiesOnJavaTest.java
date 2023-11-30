package com.example.servicediary;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesOnJavaTest {
    @Test
    public void propertyTest() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\test\\resources\\application.properties");
        properties.load(fileInputStream);
        System.out.println(properties.getProperty("mentor.admin.user-name"));
    }
}
