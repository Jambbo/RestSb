package com.example.restsb.service;

import com.example.restsb.domain.Stock;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URISyntaxException;
import java.util.List;

public interface StockService {

    void saveStockData(TickerRequestDto request) throws JsonProcessingException, URISyntaxException;

    List<SavedStockDataDto> getStocksByTicker(String ticker);

}
