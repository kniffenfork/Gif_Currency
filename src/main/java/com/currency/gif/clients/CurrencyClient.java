package com.currency.gif.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Currency", url = "${currency.client.url}")
public interface CurrencyClient {

    @RequestMapping(path= "/latest.json?app_id=${EXCHANGE.API.ID}&base=${EXCHANGE.BASE.TAG}", method = RequestMethod.GET)
    ResponseEntity<String> getTodayCurrency();

    @RequestMapping(path="/historical/{DATE}.json?app_id=${EXCHANGE.API.ID}&base=${EXCHANGE.BASE.TAG}", method = RequestMethod.GET)
    ResponseEntity<String> getYesterdayCurrency(@PathVariable("DATE") String DATE);
}
