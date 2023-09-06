package com.example.TradingApp.tradeBot;

import com.example.TradingApp.model.Item;
import com.example.TradingApp.model.Items;
import com.example.TradingApp.service.DMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class Engine {

    @Scheduled(fixedRate = 3500)
    public void monitorMarket() {

        log.info("test on");
        Items newestItems = new Items(DMarketService.getItemsFromMarket());

            Item bestDiscountItem = newestItems.items.get(0);




        Items items = new Items(DMarketService.getOffersByTitle(bestDiscountItem.title()));

        items.items = Items.removeWrongItemTitle(items.items, bestDiscountItem.title());
        if(items.items.size()>1){
            log.info(bestDiscountItem.title() + items.items.get(0).discount() + " price : " + items.items.get(0).price() + " DIFFERENCE :  " + items.items.get(0).price()/items.items.get(1).price());

        }

        if(items.items.size()>15)
        {
            if(items.items.get(0).price()/items.items.get(1).price() < 0.85 && items.items.get(0).price() > 100){
                try {
                    //specify path to save best offers
                    String filePath = "PATH";

                    FileWriter fileWriter = new FileWriter(filePath, true);


                    PrintWriter printWriter = new PrintWriter(fileWriter);


                    String outputMessage = "BEST OFFER : DIFFERENCE " + items.items.get(0).price() / items.items.get(1).price() +
                            " ITEM : " + items.items.get(0).title() + " " + items.items.get(0).price();


                    log.info(outputMessage);
                    printWriter.println(outputMessage);


                    printWriter.close();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }        }
        }



    }
}
