package com.example.TradingApp.model;

import com.example.TradingApp.jsonParser.JsonParser;
import com.example.TradingApp.service.DMarketService;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class Items {
    public List<Item> items;

    /**
     * Parse Json list to Item list
     */
    public Items() {
       this.items = sortByDiscount(itemsToList());
    }

    private List<Item> itemsToList(){
        JsonNode rootNode = JsonParser.parse(DMarketService.getItemsFromMarket());
        JsonNode objectsNode = rootNode.get("objects");
        this.items = new ArrayList<>();

        if (objectsNode != null && objectsNode.isArray()) {
            for (JsonNode object : objectsNode) {
                Item item = new Item(
                        object.get("title").asText()
                        , object.get("inMarket").asBoolean()
                        , object.get("status").asText()
                        , object.get("price").get("USD").asInt()
                        , "USD"
                        , object.get("discount").asInt()
                        , object.get("extra").get("offerId").asText()
                        , object.get("type").asText()
                );

                this.items.add(item);
            }
        }
        return items;
    }

    private List<Item> sortByDiscount(List<Item> unSortedList){
        Comparator<Item> itemComparator = Comparator.comparingInt(Item::discount);
        unSortedList.sort(itemComparator.reversed());

        return unSortedList;
    }

    public void printItems() {
        for (Item item : items) {
            System.out.printf("Item[title=%-60s, inMarket=%s, status=%s, price=%s, currency=%s, discount=%d, offerId=%s, type=%s]%n",
                    item.title(), item.inMarket(), item.status(), item.price(), item.currency(), item.discount(), item.offerId(), item.title());
        }

    }


}
