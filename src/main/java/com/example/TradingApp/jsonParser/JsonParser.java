package com.example.TradingApp.jsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        //configuration

        return defaultObjectMapper;
    }
    public static JsonNode parse(String jsonSrc) {
        jsonSrc = jsonSrc.substring(11);

        try {
            return objectMapper.readTree(jsonSrc);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
