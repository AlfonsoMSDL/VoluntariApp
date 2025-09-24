package com.proyecto.v1.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper<T> {
    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(T toConvert) {
        try {
            return mapper.writeValueAsString(toConvert);
        } catch (JsonProcessingException e) {

            throw new RuntimeException("Error al convertir objeto a JSON", e);
        }
    }

    public T fromJson(String json, Class<T> toConvert) {
        try {
            return mapper.readValue(json, toConvert);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir json a objeto", e);
        }
    }
}
