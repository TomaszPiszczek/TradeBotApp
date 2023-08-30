package com.example.TradingApp.WebClient;

import com.example.TradingApp.service.DMarketService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
@Slf4j
class DMarketWebApiTest {

    @Test
    void getAccountInfo() {
        String expected = "Piszczu";
        String response = DMarketService.getAccountInfo();

        log.info(response);

        assertTrue(response.contains(expected));
    }
    @Test
    void getAccountBalance() {
        String expected = "usdAvailableToWithdraw";
        String response = DMarketService.getAccountBalance();

        log.info(response);

        assertTrue(response.contains(expected));
    }

    @Test
    void getUserInventory() {
        String expected = "steam";
        String response = DMarketService.getUserInventory();

        log.info(response);

        assertTrue(response.contains(expected));
    }

   @Test
    void buyItem(){
        String expected = "objects";
        String response = DMarketService.getOffersByTitle("talon");

        log.info(response);

        assertTrue(response.contains(expected));
    }

    @Test
    void getItemFromMarket(){
        String expected = "objects";
        String response =  DMarketService.getItemsFromMarket();

        log.info(response);

        assertTrue(response.contains(expected));
    }



}