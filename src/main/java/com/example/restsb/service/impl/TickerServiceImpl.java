package com.example.restsb.service.impl;

import com.example.restsb.domain.Ticker;
import com.example.restsb.repository.TickerRepository;
import com.example.restsb.service.TickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TickerServiceImpl implements TickerService {
    private final TickerRepository tickerRepository;

    @Override
    public Ticker getByName(String name) {
        return tickerRepository.findByName(name);
    }
}
