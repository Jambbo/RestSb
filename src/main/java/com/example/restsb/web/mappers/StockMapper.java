package com.example.restsb.web.mappers;

import com.example.restsb.domain.Stock;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.StockDataDto;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface StockMapper {
    default List<SavedStockDataDto> stocksToSavedStockDataDtos(List<Stock> stocks) {
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
