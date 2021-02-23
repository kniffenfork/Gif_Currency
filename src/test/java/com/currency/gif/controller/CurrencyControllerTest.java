package com.currency.gif.controller;

import com.currency.gif.clients.CurrencyClient;
import com.currency.gif.clients.GifClient;
import com.currency.gif.models.currency.CurrencyResponse;
import com.currency.gif.models.currency.builder.CurrencyResponseBuilder;
import com.currency.gif.services.CurrencyService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CurrencyControllerTest {

    @MockBean
    CurrencyClient currencyClient;

    @MockBean
    GifClient gifClient;

    @Autowired
    private WebController webController;

    private Gson gson;
    private CurrencyService currencyService;
    private Map<String, String> brokeResponse;
    private Map<String, String> richResponse;
    private Map<String, String> miracleResponse;

    @BeforeEach
    void setup() {
        richResponse = new LinkedHashMap<>();
        brokeResponse = new LinkedHashMap<>();
        miracleResponse = new LinkedHashMap<>();

        richResponse.put("Type of GIF", "rich");
        brokeResponse.put("Type of GIF", "broke");
        miracleResponse.put("Type of GIF", "miracle");

        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
        currencyService = new CurrencyService();
    }

    @Test
    void generalTestingRichResponse() {
        Map<String, Double> testRatesYesterday = new LinkedHashMap<>();
        testRatesYesterday.put("USD", 1.00);
        testRatesYesterday.put("RUB", 80.75);

        Map<String, Double> testRatesToday = new LinkedHashMap<>();
        testRatesToday.put("USD", 1.00);
        testRatesToday.put("RUB", 90.00);

        CurrencyResponseBuilder currencyResponseBuilder = new CurrencyResponseBuilder();
        CurrencyResponse yesterdayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesYesterday)
                .getResponse();

        CurrencyResponse todayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesToday)
                .getResponse();

        Mockito.when(currencyClient.getTodayCurrency()).thenReturn(new ResponseEntity<>(gson.toJson(todayCurrResponse), HttpStatus.OK));

        Mockito.when(currencyClient.getYesterdayCurrency(currencyService.getYesterdayDateString())).
                thenReturn(new ResponseEntity<>(gson.toJson(yesterdayCurrResponse), HttpStatus.OK));

        Mockito.when(gifClient.returnRandomRichGif())
                                    .thenReturn(new ResponseEntity<>(richResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomBrokeGif()).
                thenReturn(new ResponseEntity<>(brokeResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomMiracleGif()).
                thenReturn(new ResponseEntity<>(miracleResponse, HttpStatus.OK));

        assertEquals(richResponse, webController.getRandomGifByCurrencyCourse("RUB").getBody());
    }

    @Test
    void generalTestingBrokeResponse() {
        Map<String, Double> testRatesYesterday = new LinkedHashMap<>();
        testRatesYesterday.put("USD", 1.00);
        testRatesYesterday.put("RUB", 90.00);

        Map<String, Double> testRatesToday = new LinkedHashMap<>();
        testRatesToday.put("USD", 1.00);
        testRatesToday.put("RUB", 80.75);

        CurrencyResponseBuilder currencyResponseBuilder = new CurrencyResponseBuilder();
        CurrencyResponse yesterdayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesYesterday)
                .getResponse();

        CurrencyResponse todayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesToday)
                .getResponse();

        Mockito.when(currencyClient.getTodayCurrency()).thenReturn(new ResponseEntity<>(gson.toJson(todayCurrResponse), HttpStatus.OK));

        Mockito.when(currencyClient.getYesterdayCurrency(currencyService.getYesterdayDateString())).
                thenReturn(new ResponseEntity<>(gson.toJson(yesterdayCurrResponse), HttpStatus.OK));

        Mockito.when(gifClient.returnRandomRichGif())
                .thenReturn(new ResponseEntity<>(richResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomBrokeGif()).
                thenReturn(new ResponseEntity<>(brokeResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomMiracleGif()).
                thenReturn(new ResponseEntity<>(miracleResponse, HttpStatus.OK));

        assertEquals(brokeResponse, webController.getRandomGifByCurrencyCourse("RUB").getBody());
    }

    @Test
    void generalTestingMiracleResponse() {

        Map<String, Double> testRatesYesterday = new LinkedHashMap<>();
        testRatesYesterday.put("USD", 1.00);
        testRatesYesterday.put("RUB", 90.00);

        Map<String, Double> testRatesToday = new LinkedHashMap<>();
        testRatesToday.put("USD", 1.00);
        testRatesToday.put("RUB", 80.75);

        CurrencyResponseBuilder currencyResponseBuilder = new CurrencyResponseBuilder();
        CurrencyResponse yesterdayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesYesterday)
                .getResponse();

        CurrencyResponse todayCurrResponse = currencyResponseBuilder.createResponse()
                .setDisclaimer("TestDisclaimer")
                .setLicense("TestLicense")
                .setTimeStamp(1234567L)
                .setBase("USD")
                .setRates(testRatesToday)
                .getResponse();

        Mockito.when(currencyClient.getTodayCurrency()).thenReturn(new ResponseEntity<>(gson.toJson(todayCurrResponse), HttpStatus.OK));

        Mockito.when(currencyClient.getYesterdayCurrency(currencyService.getYesterdayDateString())).
                thenReturn(new ResponseEntity<>(gson.toJson(yesterdayCurrResponse), HttpStatus.OK));

        Mockito.when(gifClient.returnRandomRichGif())
                .thenReturn(new ResponseEntity<>(richResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomBrokeGif()).
                thenReturn(new ResponseEntity<>(brokeResponse, HttpStatus.OK));
        Mockito.when(gifClient.returnRandomMiracleGif()).
                thenReturn(new ResponseEntity<>(miracleResponse, HttpStatus.OK));

        assertEquals(miracleResponse, webController.getRandomGifByCurrencyCourse("USD").getBody());
    }
}
