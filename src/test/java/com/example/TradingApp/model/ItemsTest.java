package com.example.TradingApp.model;

import com.example.TradingApp.service.DMarketService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsTest {
    @Test
    void constructorCreateListOfNewestItemsOnMarket(){
        Items items = new Items(DMarketService.getItemsFromMarket());
        items.printItems();
        assertTrue(items.items.size()>49);

    }

    @Test
    void constructorCreateListOfSpecifiedItemsOnMarket(){
        Items newestItems = new Items(DMarketService.getItemsFromMarket());

        Item bestDiscountItem = newestItems.items.get(0);

        System.out.println(bestDiscountItem.title() + bestDiscountItem.discount());

        Items items = new Items(DMarketService.getOffersByTitle(bestDiscountItem.title()));

       // System.out.println(DMarketService.getOffersByTitle("AWP | Exoskeleton (Field-Tested) "));
        items.items = Items.removeWrongItemTitle(items.items, bestDiscountItem.title());

        items.printItems();
        assertTrue(items.items.size()>1);

    }

}