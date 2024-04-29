package com.example.restsb.web.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TickerRequestDto {

    private String ticker;
    private LocalDate start;
    private LocalDate end;

}
