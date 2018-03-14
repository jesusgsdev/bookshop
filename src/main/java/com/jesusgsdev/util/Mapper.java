package com.jesusgsdev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Mapper {

    @Bean
    ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    public <T> T mapToObject(String json, Class<T> clazz) throws IOException {
        return getMapper().readValue(json, clazz);
    }

    public String mapToJSON(Object o) throws JsonProcessingException {
        return getMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }
}
