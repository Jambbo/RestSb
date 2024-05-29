package com.example.restsb.service;

import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import java.util.List;

public interface StockService {

    void saveStockData(TickerRequestDto request);
    List<SavedStockDataDto> getStocksByTicker(String ticker);

}
