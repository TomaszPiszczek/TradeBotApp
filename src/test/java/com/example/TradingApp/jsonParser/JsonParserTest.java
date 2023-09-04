package com.example.TradingApp.jsonParser;

import com.example.TradingApp.service.DMarketService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Slf4j
class JsonParserTest {
    @Test
    void parseTestShouldReturnTitle() {
        String json = DMarketService.getItemsFromMarket();
        JsonNode rootNode = JsonParser.parse(json);
        JsonNode objectsNode = rootNode.get("objects");
        String title="";
        if (objectsNode.isArray()) {
            for (JsonNode object : objectsNode) {
                 title = "title " +  object.get("title").asText() +" "+ object.get("price").get("USD").asText();

                log.info(title);
            }
        }
        assertTrue(title.contains("title"));




    }

}