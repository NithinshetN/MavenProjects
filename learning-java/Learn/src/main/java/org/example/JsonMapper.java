package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonMapper {
    public List<Quiz> display(String name) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("C:/Users/nithi/Desktop/java/Learn/src/main/resources/"+name), new TypeReference<List<Quiz>>() {});
    }

}
