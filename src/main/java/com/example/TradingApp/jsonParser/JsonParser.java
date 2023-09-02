package com.example.TradingApp.jsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        //configuration

        return defaultObjectMapper;
    }
    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {
        return objectMapper.readTree(jsonSrc);
    }



}
