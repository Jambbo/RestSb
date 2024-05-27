package com.example.restsb.web.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SavedStockDataDto {

     String id;
     String ticker;
     List<StockDataDto> data;

}
