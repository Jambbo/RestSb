package com.example.restsb.web.controller;

import com.example.restsb.domain.Stock;
import com.example.restsb.domain.Ticker;
import com.example.restsb.exceptions.ValidationException;
import com.example.restsb.service.StockService;
import com.example.restsb.service.TickerService;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final StockService stockService;
    private final TickerService tickerService;
    @Value("${api.key}")
    private String apiKey;

    @PostMapping("/save")
    public ResponseEntity<String> saveStockInfo(@RequestBody TickerRequestDto request){
        if(request.getStart().isAfter(request.getEnd())){
            throw new ValidationException("Start date can not be after end date");
        }
        Ticker ticker = tickerService.getByName(request.getTicker());
        if(ticker==null){
            throw new ValidationException("Unknown ticker: "+request.getTicker());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = request.getStart().format(formatter);
        String endDate = request.getEnd().format(formatter);

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.polygon.io/v2/aggs/ticker/"+request.getTicker()+
                "/range/1/day/"+startDate+"/"+endDate+"?apiKey="+apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);

        stockService.saveStockData(response.getBody());
        return ResponseEntity.ok("Stock data fetched and saved successfully.");
    }

    @GetMapping("/saved{ticker}")
    public ResponseEntity<List<SavedStockDataDto>> getStockInfoByTicker(@RequestParam("ticker") String ticker){
        List<SavedStockDataDto> stocksByTicker = stockService.getStocksByTicker(ticker);
        return new ResponseEntity<>(stocksByTicker,HttpStatus.OK);
    }

}
