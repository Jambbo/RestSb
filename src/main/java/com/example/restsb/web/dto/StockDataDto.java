package com.example.restsb.web.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockDataDto {
     LocalDate date;
     Double open;
     Double close;
     Double high;
     Double low;

}
