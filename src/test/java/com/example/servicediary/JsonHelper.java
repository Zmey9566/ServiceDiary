package com.example.servicediary;

import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonHelper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String jsonPath, Class<T> out) {
        try {
            return mapper.readValue(new File(jsonPath), out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
