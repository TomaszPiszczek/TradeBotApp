package com.example.TradingApp.model;

public record Item(String title, boolean inMarket, String status, double price, String currency, int discount, String offerId, String type, String category) {
}
