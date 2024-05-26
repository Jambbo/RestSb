package com.example.restsb.service.impl;

import com.example.restsb.client.PolygonClient;
import com.example.restsb.domain.Result;
import com.example.restsb.domain.Stock;
import com.example.restsb.exceptions.ValidationException;
import com.example.restsb.repository.ResultRepository;
import com.example.restsb.repository.StockRepository;
import com.example.restsb.service.StockService;
import com.example.restsb.service.validators.Validator;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.StockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import com.example.restsb.web.mappers.StockMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ResultRepository resultRepository;
    private final PolygonClient client;
    private final Validator validator;
    private final StockMapper stockMapper;
    @Override
    public void saveStockData(TickerRequestDto request) throws JsonProcessingException, URISyntaxException {
        validator.isAfter(request);
        validator.isTickerNull(request);
        RestTemplate restTemplate = new RestTemplate();
        String uri = client.getUri(request);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Stock stock = objectMapper.readValue(response.getBody(), Stock.class);
        List<Result> results = stock.getResults();
        results.forEach(r -> r.setStock(stock));
        stockRepository.save(stock);
        List<Result> existingResults = resultRepository.findAllByStockAndTIn(stock.getId(), results.stream().map(Result::getTime).collect(Collectors.toList()));
        List<Result> newResults = results.stream()
                .filter(r -> existingResults.stream().noneMatch(er -> er.getTime().equals(r.getTime())))
                .toList();
        stock.setResults(newResults);
        resultRepository.saveAll(results);
    }

    @Override
    public List<SavedStockDataDto> getStocksByTicker(String ticker){
        List<Stock> stocks = stockRepository.findStocksByTicker(ticker);
        if(stocks.isEmpty()){
            throw new ValidationException("Unknown ticker: "+ticker);
        }
        return stocks.stream()
                .map(stockMapper::toSavedStockDataDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<SavedStockDataDto> getStocksByTicker(String ticker) {
//        List<Stock> stocks =  stockRepository.findStocksByTicker(ticker);
//        if(stocks.isEmpty()){
//            throw new ValidationException("Unknown ticker: "+ticker);
//        }
//        return stocks.stream().map(stock -> {
//            SavedStockDataDto savedStockDataDto = SavedStockDataDto.builder()
//                    .id(stock.getId().toString())
//                    .ticker(stock.getTicker())
//                    .data(stock.getResults().stream().map(result -> {
//                        StockDataDto stockDataDto = StockDataDto.builder()
//                                .date(Instant.ofEpochMilli(result.getTime()).atZone(ZoneId.systemDefault()).toLocalDate())
//                                .open(result.getOpenPrice())
//                                .close(result.getClosePrice())
//                                .high(result.getHighPrice())
//                                .low(result.getLowPrice())
//                                .build();
//                            return stockDataDto;
//                    }).collect(Collectors.toList()))
//                    .build();
//            return savedStockDataDto;
//        }).collect(Collectors.toList());
//
//    }
}
