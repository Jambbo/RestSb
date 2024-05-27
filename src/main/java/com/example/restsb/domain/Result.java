package com.example.restsb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "results")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("v")
    @Column(name = "volume")
    private Long volumePrice;

    @JsonProperty("vw")
    @Column(name = "volume_weight")
    private Double volumeWeightPrice;

    @JsonProperty("o")
    @Column(name = "open")
    private Double openPrice;

    @JsonProperty("c")
    @Column(name = "close")
    private Double closePrice;

    @JsonProperty("h")
    @Column(name = "high")
    private Double highPrice;

    @JsonProperty("l")
    @Column(name = "low")
    private Double lowPrice;

    @JsonProperty("t")
    private Long time;

    @JsonProperty("n")
    @Column(name = "number")
    private Long numberOfTransactions;

    @ManyToOne
    private Stock stock;

}
