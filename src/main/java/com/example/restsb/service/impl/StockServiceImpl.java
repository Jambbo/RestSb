package com.example.restsb.service.impl;

import com.example.restsb.client.PolygonClient;
import com.example.restsb.domain.Result;
import com.example.restsb.domain.Stock;
import com.example.restsb.exceptions.ValidationException;
import com.example.restsb.repository.ResultRepository;
import com.example.restsb.repository.StockRepository;
import com.example.restsb.service.StockService;
import com.example.restsb.validators.Validator;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import com.example.restsb.web.mappers.StockMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final PolygonClient client;
    private final StockRepository stockRepository;
    private final Validator validator;
    private final StockMapper stockMapper;
    private final ResultRepository resultRepository;

    @SneakyThrows
    @Override
    @Transactional
    @Cacheable(value = "StockService::saveStockData",key="#request")
    public void saveStockData(TickerRequestDto request) {
        validator.dateValidation(request);
        validator.isTickerNull(request);
        ResponseEntity<Stock> stockResponseEntity = client.contactToGetStock(request);
        Stock stock = stockResponseEntity.getBody();
        validator.isStockNull(stock);
        List<Result> results = stock.getResults();
        List<Result> res = resultRepository.findByTimeRangeAndTicker(
                request.getStart().atStartOfDay().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli(),
                request.getEnd().atStartOfDay().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli(),
                stock.getTicker());
       List<Result> newResults = new ArrayList<>();
        findDuplicates(results, res, stock, newResults);
        stock.setResults(newResults);
        stockRepository.save(stock);
    }

    @Override
    public List<SavedStockDataDto> getStocksByTicker(String ticker) {
        List<Stock> stocks = stockRepository.findStocksByTicker(ticker);
        if (stocks.isEmpty()) {
            throw new ValidationException("Unknown ticker: " + ticker);
        }
        return stockMapper.stocksToSavedStockDataDtos(stocks);
    }

    private static void findDuplicates(List<Result> results, List<Result> res, Stock stock, List<Result> newResults) {
        for(Result r: results){
            Long time = r.getTime();
            boolean found = false;
            for(Result rr: res){
                if(Objects.equals(rr.getTime(),time)){
                    found = true;
                    break;
                }
            }
            if(!found) {
                r.setStock(stock);
                newResults.add(r);
            }
        }
    }
}
