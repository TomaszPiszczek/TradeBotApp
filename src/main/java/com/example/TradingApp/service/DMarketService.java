package com.example.TradingApp.service;

import com.example.TradingApp.WebClient.DMarketWebApi;
import org.springframework.stereotype.Service;

@Service
public class DMarketService {


    public static String getAccountInfo() {

        String path = "/account/v1/user";
        String method ="GET";
        return DMarketWebApi.getResponse(path, method);
    }

    public static String getAccountBalance() {

        String path = "/account/v1/balance";
        String method ="GET";
        return DMarketWebApi.getResponse(path, method);
    }
    public static String getUserInventory() {

        String path = "/marketplace-api/v1/user-inventory?GameID=a8db&SortType=UserInventorySortTypeDefault&Presentation=InventoryPresentationSimple";
        String method ="GET";
        return DMarketWebApi.getResponse(path, method);
    }

    public static String getOffersByTitle(String title) {

        String path = "/exchange/v1/offers-by-title?gameId=a8db&Title=" + title +"&Limit=1000";
        String  method ="GET";
        return DMarketWebApi.getResponse(path, method);
    }
    public static String getItemsFromMarket(){
        String path = "/exchange/v1/market/items?gameId=a8db&limit=10&orderBy=updated&orderDir=desc&currency=USD&priceFrom=100";
        String method ="GET";
        return DMarketWebApi.getResponse(path, method);
    }




}
