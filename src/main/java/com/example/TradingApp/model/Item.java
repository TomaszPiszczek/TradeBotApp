package com.example.TradingApp.model;

public record Item(String title,boolean inMarket,String status,int price,String currency,int discount,String offerId,String type) {
}
