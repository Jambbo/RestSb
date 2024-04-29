package com.example.restsb.service;

import com.example.restsb.domain.Stock;
import com.example.restsb.web.dto.SavedStockDataDto;

import java.util.List;

public interface StockService {

    void saveStockData(String responseBody);

    List<SavedStockDataDto> getStocksByTicker(String ticker);

}
