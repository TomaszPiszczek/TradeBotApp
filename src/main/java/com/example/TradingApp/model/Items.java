package com.example.TradingApp.model;

import com.example.TradingApp.jsonParser.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Items {
    public List<Item> items;

    /**
     * Parse Json list to Item list
     */
    public Items(String jsonSrc) {
       this.items = sortByDiscount(itemsToList(jsonSrc));
    }

    private List<Item> itemsToList(String jsonSrc){

        JsonNode rootNode = JsonParser.parse(jsonSrc);
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
                        , object.get("extra").get("category").asText()
                );
                if(item.inMarket()){
                   items.add(item);
                }
            }
        }
        return items;
    }

    private List<Item> sortByDiscount(List<Item> unSortedList){
        Comparator<Item> itemComparator = Comparator.comparingInt(Item::discount);
        unSortedList.sort(itemComparator.reversed());

        if(unSortedList.size()>100){
            unSortedList = unSortedList.subList(0,100);
        }


        return unSortedList;
    }

    public static List<Item> removeWrongItemTitle(List<Item> list, String title) {

        list = list.stream()
                .filter(item -> item.title().equals(title))
                .collect(Collectors.toList());

        return list;
    }


    public void printItems() {
        for (Item item : items) {
            System.out.printf("Item[title=%-60s, inMarket=%s, status=%s, price=%s, currency=%s, discount=%d, offerId=%s, type=%s]%n",
                    item.title(), item.inMarket(), item.status(), item.price(), item.currency(), item.discount(), item.offerId(), item.title());
        }

    }


}
