package com.example.restsb.service.impl;

import com.example.restsb.domain.Ticker;
import com.example.restsb.repository.TickerRepository;
import com.example.restsb.service.TickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TickerServiceImpl implements TickerService {
    private final TickerRepository tickerRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "TickerService::getByName",key = "#name")
    public Ticker getByName(String name) {
        return tickerRepository.findByName(name);
    }
}
