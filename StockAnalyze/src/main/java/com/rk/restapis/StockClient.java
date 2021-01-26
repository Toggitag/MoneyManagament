package com.rk.restapis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class StockClient {

    @Value("{sma.url.moneycontrol}")
    private String moneyControlUrl;
    @Autowired
    private WebClient webClient;

    public  Mono<List<String>>  retrieveInfo(){
      return webClient.get().uri(moneyControlUrl).retrieve().bodyToFlux(String.class).collectList();
    }

}
