package com.example.restsb.service.validators;

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

    public void isAfter(TickerRequestDto requestDto){
        LocalDate requestStart = requestDto.getStart();
        LocalDate requestEnd = requestDto.getEnd();
        if(requestStart.isAfter(requestEnd)){
            throw new ValidationException("Start date can not be after end date");
        }
    }

    public void isTickerNull(TickerRequestDto request){
        Ticker ticker = tickerService.getByName(request.getTicker());
        if(isNull(ticker)){
            throw new ValidationException("Unknown ticker: "+request.getTicker());
        }
    }

}
