package com.currency.gif.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name="GifClient", url="${gif.client.url}")
public interface GifClient {

    @RequestMapping(path = "/random?api_key=${GIF.API.ID}&tag=${FIRST.TAG.GIF}", method= RequestMethod.GET)
    ResponseEntity<Map> returnRandomRichGif();

    @RequestMapping(path = "/random?api_key=${GIF.API.ID}&tag=${SECOND.TAG.GIF}")
    ResponseEntity<Map> returnRandomBrokeGif();

    @RequestMapping(path="/random?api_key=${GIF.API.ID}&tag=${THIRD.TAG.GIF}")
    ResponseEntity<Map> returnRandomMiracleGif();
}
