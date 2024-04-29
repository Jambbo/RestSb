package com.example.restsb.service;

import com.example.restsb.domain.Ticker;

public interface TickerService {

    Ticker getByName(String name);

}
