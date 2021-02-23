package com.currency.gif.models.currency.builder;

import com.currency.gif.models.currency.CurrencyResponse;

import java.util.Map;

public class CurrencyResponseBuilder {

    private CurrencyResponse currencyResponse;

    public CurrencyResponseBuilder createResponse() {
        this.currencyResponse = new CurrencyResponse();
        return this;
    }

    public CurrencyResponseBuilder setDisclaimer(String disclaimer) {
        currencyResponse.setDisclaimer(disclaimer);
        return this;
    }

    public CurrencyResponseBuilder setLicense(String license) {
        currencyResponse.setLicense(license);
        return this;
    }

    public CurrencyResponseBuilder setTimeStamp(Long timeStamp) {
        currencyResponse.setTimestamp(timeStamp);
        return this;
    }

    public CurrencyResponseBuilder setBase(String base) {
        currencyResponse.setBase(base);
        return this;
    }

    public CurrencyResponseBuilder setRates(Map<String, Double> rates) {
        currencyResponse.setRates(rates);
        return this;
    }

    public CurrencyResponse getResponse() {
        return currencyResponse;
    }
}
