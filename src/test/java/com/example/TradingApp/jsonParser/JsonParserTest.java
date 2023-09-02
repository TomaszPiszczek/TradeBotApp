package com.example.TradingApp.jsonParser;

import com.example.TradingApp.service.DMarketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {
    @Test
    void parseTest() throws JsonProcessingException {
        String json = DMarketService.getItemsFromMarket();
        json = json.substring(11);
        JsonNode node = JsonParser.parse(json);
       // String title = node.get("title").asText();
        //System.out.println(title);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(json);
    }

}