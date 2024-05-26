package com.example.restsb.web.mappers;

import com.example.restsb.domain.Stock;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.StockDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZoneId;

@Mapper(componentModel = "spring", imports = {Instant.class, ZoneId.class})
public interface StockMapper {


    @Mapping(target = "date", expression = "java(Instant.ofEpochMilli(stock.getResults().get(0).getTime()).atZone(ZoneId.systemDefault()).toLocalDate())")
    StockDataDto toStockDataDto(Stock stock);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "ticker", source = "ticker")
    @Mapping(target = "data", source = "results")
    SavedStockDataDto toSavedStockDataDto(Stock stock);

}
