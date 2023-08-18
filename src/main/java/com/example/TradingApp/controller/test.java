package com.example.TradingApp.controller;

import com.example.TradingApp.WebClient.DMarketWebApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class test {

    private DMarketWebApi dMarketApiWebService;


    public test(DMarketWebApi dMarketApiService) {
        this.dMarketApiWebService = dMarketApiService;
    }


    @GetMapping("/get")
    public String get() throws Exception {
        dMarketApiWebService.getCall();

        return "";
    }

}
