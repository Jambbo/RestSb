package com.example.restsb.web.controller;



import com.example.restsb.service.StockService;
import com.example.restsb.web.dto.SavedStockDataDto;
import com.example.restsb.web.dto.TickerRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final StockService stockService;

    @PostMapping("/save")
    public ResponseEntity<String> saveStockInfo(@RequestBody TickerRequestDto request){
        stockService.saveStockData(request);
        return ResponseEntity.ok("Stock data fetched and saved successfully.");
    }

    @GetMapping("/saved{ticker}")
    public ResponseEntity<List<SavedStockDataDto>> getStockInfoByTicker(@RequestParam("ticker") String ticker){
        List<SavedStockDataDto> stocksByTicker = stockService.getStocksByTicker(ticker);
        return new ResponseEntity<>(stocksByTicker,HttpStatus.OK);
    }

}
