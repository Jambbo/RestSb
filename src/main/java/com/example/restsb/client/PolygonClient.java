package com.example.restsb.client;


import com.example.restsb.domain.Stock;
import com.example.restsb.web.dto.TickerRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PolygonClient {
    @Value("${api.key}")
    String apiKey;

    public ResponseEntity<Stock> contactToGetStock(TickerRequestDto request){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = request.getStart().format(formatter);
        String endDate = request.getEnd().format(formatter);
        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromUriString("https://api.polygon.io/v2/aggs/ticker/")
                .pathSegment(request.getTicker(),"range","1","day",startDate,endDate)
                .queryParam("apiKey",apiKey)
                .build()
                .toUri();

//        String url = "https://api.polygon.io/v2/aggs/ticker/"+request.getTicker()+
//                "/range/1/day/"+startDate+"/"+endDate+"?apiKey="+apiKey;

        return restTemplate.getForEntity(uri, Stock.class);
    }
}

