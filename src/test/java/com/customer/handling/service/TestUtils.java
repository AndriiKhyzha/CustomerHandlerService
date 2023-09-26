package com.customer.handling.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T readValue(Class<?> thisClass, Class<T> deserializedClass, String fileName) {
        InputStream inputStream = thisClass.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File " + fileName + " not found!");
        }

        try {
            return objectMapper.readValue(inputStream, deserializedClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
