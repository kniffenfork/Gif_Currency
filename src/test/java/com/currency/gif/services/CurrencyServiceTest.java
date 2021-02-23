package com.currency.gif.services;

import com.currency.gif.models.currency.CurrencyResponse;
import com.currency.gif.models.currency.builder.CurrencyResponseBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {

    private CurrencyService currencyService;

    @BeforeEach
    public void setup() {
        this.currencyService = new CurrencyService();
    }

    @Test
    void yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        assertEquals(cal.getTime(), currencyService.yesterday());
    }

    @Test
    void getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals(dateFormat.format(currencyService.yesterday()), currencyService.getYesterdayDateString());
    }

    @Test
    void mappingJsonToJavaCurrencyObject() {

        Map<String, Double> testRates = new LinkedHashMap<>();
        testRates.put("testOne", 1.23);
        testRates.put("testTwo", 2.34);
        
        String json = "{\"disclaimer\": \"TestDisclaimer\",\n" +
                "\"license\": \"TestLicense\",\n" +
                "\"timestamp\": 1234567,\n" +
                "\"base\": \"USD\",\n" +
                "\"rates\": {\n" +
                "\"testOne\": 1.23, \"testTwo\": 2.34\n}\n}";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(json, HttpStatus.OK);
        CurrencyResponseBuilder currencyResponseBuilder = new CurrencyResponseBuilder();
        CurrencyResponse expectedResponse = currencyResponseBuilder.createResponse()
                                                                    .setDisclaimer("TestDisclaimer")
                                                                    .setLicense("TestLicense")
                                                                    .setTimeStamp(1234567L)
                                                                    .setBase("USD")
                                                                    .setRates(testRates)
                                                                    .getResponse();

        assertEquals(expectedResponse, currencyService.mappingJsonToJavaCurrencyObject(responseEntity));
    }
}