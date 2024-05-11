package com.example.restsb.service.impl;

import com.example.restsb.domain.Result;
import com.example.restsb.domain.Stock;
import com.example.restsb.exceptions.ValidationException;
import com.example.restsb.repository.ResultRepository;
import com.example.restsb.repository.StockRepository;
import com.example.restsb.service.StockService;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.StockDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ResultRepository resultRepository;
    @Override
    public void saveStockData(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode responseNode = mapper.readTree(responseBody);
            JsonNode resultsNode = responseNode.get("results");
            Stock stock = Stock.builder()
                    .ticker(responseNode.get("ticker").asText())
                    .queryCount(responseNode.get("queryCount").asLong())
                    .resultsCount(responseNode.get("resultsCount").asLong())
                    .isAdjusted(responseNode.get("adjusted").asBoolean())
                    .status(responseNode.get("status").asText())
                    .requestId(responseNode.get("request_id").asText())
                    .count(responseNode.get("count").asInt())
                    .build();
            List<Result> resultList = new ArrayList<>();

                for(JsonNode resultNode : resultsNode){
                    Long time = resultNode.get("t").asLong();
                    Result result = Result.builder()
                            .stock(stock)
                            .volumePrice(resultNode.get("v").asLong())
                            .volumeWeightPrice(resultNode.get("vw").asDouble())
                            .openPrice(resultNode.get("o").asDouble())
                            .closePrice(resultNode.get("c").asDouble())
                            .highPrice(resultNode.get("h").asDouble())
                            .lowPrice(resultNode.get("l").asDouble())
                            .time(time)
                            .numberOfTransactions(resultNode.get("n").asLong())
                            .build();
                            if((resultRepository.findByTimeAndTicker(time,stock.getTicker())).isPresent()){
                                continue;
                            }
                    resultList.add(result);
                }
                stock.setResults(resultList);
                stockRepository.save(stock);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<SavedStockDataDto> getStocksByTicker(String ticker) {
        List<Stock> stocks =  stockRepository.findStocksByTicker(ticker);
        if(stocks.isEmpty()){
            throw new ValidationException("Unknown ticker: "+ticker);
        }
        return stocks.stream().map(stock -> {
            SavedStockDataDto savedStockDataDto = SavedStockDataDto.builder()
                    .id(stock.getId().toString())
                    .ticker(stock.getTicker())
                    .data(stock.getResults().stream().map(result -> {
                        StockDataDto stockDataDto = StockDataDto.builder()
                                .date(Instant.ofEpochMilli(result.getTime()).atZone(ZoneId.systemDefault()).toLocalDate())
                                .open(result.getOpenPrice())
                                .close(result.getClosePrice())
                                .high(result.getHighPrice())
                                .low(result.getLowPrice())
                                .build();
                            return stockDataDto;
                    }).collect(Collectors.toList()))
                    .build();
            return savedStockDataDto;
        }).collect(Collectors.toList());

    }
}
