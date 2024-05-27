package com.example.restsb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("v")
    @Column(name = "volume")
    @JsonProperty("v")
    private Long volumePrice;

    @JsonProperty("vw")
    @Column(name = "volume_weight")
    @JsonProperty("vw")
    private Double volumeWeightPrice;

    @JsonProperty("o")
    @Column(name = "open")
    @JsonProperty("o")
    private Double openPrice;

    @JsonProperty("c")
    @Column(name = "close")
    @JsonProperty("c")
    private Double closePrice;

    @JsonProperty("h")
    @Column(name = "high")
    @JsonProperty("h")
    private Double highPrice;

    @JsonProperty("l")
    @Column(name = "low")
    @JsonProperty("l")
    private Double lowPrice;

    @JsonProperty("t")
    private Long time;

    @JsonProperty("n")
    @Column(name = "number")
    @JsonProperty("n")
    private Long numberOfTransactions;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

}
