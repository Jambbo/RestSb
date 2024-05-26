package com.example.restsb.domain;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @Column(name = "volume")
    @JsonProperty("v")
    private Long volumePrice;

    @Column(name = "volume_weight")
    @JsonProperty("vw")
    private Double volumeWeightPrice;

    @Column(name = "open")
    @JsonProperty("o")
    private Double openPrice;

    @Column(name = "close")
    @JsonProperty("c")
    private Double closePrice;

    @Column(name = "high")
    @JsonProperty("h")
    private Double highPrice;

    @Column(name = "low")
    @JsonProperty("l")
    private Double lowPrice;

    @JsonProperty("t")
    private Long time;

    @Column(name = "number")
    @JsonProperty("n")
    private Long numberOfTransactions;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

}
