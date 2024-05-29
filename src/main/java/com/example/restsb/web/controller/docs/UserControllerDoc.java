package com.example.restsb.web.controller.docs;

import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserControllerDoc {

    @Operation(summary = "save stock info from api to db")
    ResponseEntity<String> saveStockInfo(@RequestBody TickerRequestDto request);

    @Operation(summary = "get stock info for user from db")
    ResponseEntity<List<SavedStockDataDto>> getStockInfoByTicker(
            @PathVariable @RequestParam("ticker") String ticker);
}
