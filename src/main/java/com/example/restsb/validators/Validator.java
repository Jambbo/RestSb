package com.example.restsb.validators;

import com.example.restsb.domain.Stock;
import com.example.restsb.domain.Ticker;
import com.example.restsb.exceptions.ValidationException;
import com.example.restsb.service.TickerService;
import com.example.restsb.web.dto.TickerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class Validator {
    private final TickerService tickerService;
    public void dateValidation(TickerRequestDto request){
        LocalDate startDate = request.getStart();
        LocalDate endDate = request.getEnd();
        if(startDate.isAfter(endDate)){
            throw new ValidationException("Start date can not be after end date");
        }
    }

    public void isTickerNull(TickerRequestDto request){
        Ticker ticker = tickerService.getByName(request.getTicker());
        if(ticker==null){
            throw new ValidationException("Unknown ticker: "+request.getTicker());
        }
    }

    public void isStockNull(Stock stock){
        if(isNull(stock)){
            throw new ValidationException("Stock is null");
        }
    }



}
