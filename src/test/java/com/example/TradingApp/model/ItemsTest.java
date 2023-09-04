package com.example.TradingApp.model;

import com.example.TradingApp.service.DMarketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {
    @Test
    void constructorCreateListOfItemsOnMarket(){
        Items items = new Items(DMarketService.getItemsFromMarket());
        items.printItems();
        assertTrue(items.items.size()>49);

    }

}