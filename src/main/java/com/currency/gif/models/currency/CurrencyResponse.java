package com.currency.gif.models.currency;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyResponse {
    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;
}
