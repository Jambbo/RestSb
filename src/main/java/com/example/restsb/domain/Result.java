package com.example.restsb.domain;

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

    @Column(name = "volume")
    private Long volumePrice;

    @Column(name = "volume_weight")
    private Double volumeWeightPrice;

    @Column(name = "open")
    private Double openPrice;

    @Column(name = "close")
    private Double closePrice;

    @Column(name = "high")
    private Double highPrice;

    @Column(name = "low")
    private Double lowPrice;

    private Long time;

    @Column(name = "number")
    private Long numberOfTransactions;

    @ManyToOne
    private Stock stock;

}
