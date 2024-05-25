package com.example.restsb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "stocks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    @JsonProperty("queryCount")
    private Long queryCount;
    private Long resultsCount;

    @Column(name = "adjusted", columnDefinition = "BOOLEAN")
    @JsonProperty("adjusted")
    private Boolean isAdjusted;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_id")
    @JsonProperty("results")
    private List<Result> results = new ArrayList<>();


    private String status;

    @Column(name = "request_id")
    @JsonProperty("request_id")
    private String requestId;

    private Integer count;

    public void addResult(Result result) {
        result.setStock(this);
        this.results.add(result);
    }
}
