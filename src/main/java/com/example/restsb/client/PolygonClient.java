package com.example.restsb.client;

import com.example.restsb.web.dto.TickerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class PolygonClient {
    @Value("${api.key}")
    private String apiKey;

    public String getUri(TickerRequestDto request) throws URISyntaxException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = request.getStart().format(formatter);
        String endDate = request.getEnd().format(formatter);

        return "https://api.polygon.io/v2/aggs/ticker/"+request.getTicker()+
                "/range/1/day/"+startDate+"/"+endDate+"?apiKey="+apiKey;
    }

}
