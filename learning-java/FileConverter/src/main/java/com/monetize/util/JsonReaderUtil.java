package com.monetize.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonReaderUtil {
    public static <T> T loadFromDataFromJsonFile(String fileName, TypeReference<T> typeRef){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(JsonReaderUtil.class.getResourceAsStream(fileName),typeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
