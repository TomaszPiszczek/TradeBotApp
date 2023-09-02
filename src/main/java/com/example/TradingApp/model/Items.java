package com.example.TradingApp.model;

import java.util.LinkedList;

public class Items {
    LinkedList<Item> items;

    public Items(LinkedList<Item> items) {
        this.items = items;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }
}
