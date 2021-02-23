package com.currency.gif.controller;

import com.currency.gif.clients.CurrencyClient;
import com.currency.gif.clients.GifClient;
import com.currency.gif.models.currency.CurrencyResponse;
import com.currency.gif.services.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final CurrencyClient currencyClient;
    private final GifClient gifClient;
    private final CurrencyService currencyService;
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    public WebController(CurrencyClient currencyClient, GifClient gifClient, CurrencyService currencyService) {
        this.currencyClient = currencyClient;
        this.gifClient = gifClient;
        this.currencyService = currencyService;
    }

    @GetMapping(path = "/{currency}")
    ResponseEntity<?> getRandomGifByCurrencyCourse(@PathVariable("currency") String currency) {

        String yesterdayDate = currencyService.getYesterdayDateString();
        CurrencyResponse currencyToday = currencyService.mappingJsonToJavaCurrencyObject(currencyClient.getTodayCurrency());
        CurrencyResponse currencyYesterday = currencyService.mappingJsonToJavaCurrencyObject(currencyClient.getYesterdayCurrency(yesterdayDate));

        Double todayCourseOfCurrency = currencyToday.getRates().get(currency);
        Double yesterdayCourseOfCurrency = currencyYesterday.getRates().get(currency);

        if (todayCourseOfCurrency > yesterdayCourseOfCurrency) {
            logger.info(String.format("today course > than yesterday. Currency = %s, yesterday course = %f, today course = %f", currency,
                    currencyYesterday.getRates().get(currency), currencyToday.getRates().get(currency)));
            return new ResponseEntity<>(gifClient.returnRandomRichGif().getBody(), HttpStatus.OK);
        }

        else if (todayCourseOfCurrency.equals(yesterdayCourseOfCurrency)) {
            logger.info(String.format("today course == yesterday. Currency = %s, yesterday course = %f, today course = %f", currency,
                    currencyYesterday.getRates().get(currency), currencyToday.getRates().get(currency)));
            return new ResponseEntity<>(gifClient.returnRandomMiracleGif().getBody(), HttpStatus.OK);
        }

        else if (todayCourseOfCurrency < yesterdayCourseOfCurrency) {
            logger.info(String.format("today course < yesterday. Currency = %s, yesterday course = %f, today course = %f", currency,
                    currencyYesterday.getRates().get(currency), currencyToday.getRates().get(currency)));
            return new ResponseEntity<>(gifClient.returnRandomBrokeGif().getBody(), HttpStatus.OK);
        }
        else {
            logger.info("Existed error. Currency = " + currency);
            return new ResponseEntity<>("Here is unexpected error. What a pity!", HttpStatus.BAD_REQUEST);
        }
    }
}
