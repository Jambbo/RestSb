package com.example.restsb.domain;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private Long queryCount;
    private Long resultsCount;

    @JsonProperty("adjusted")
    @Column(name = "adjusted", columnDefinition = "BOOLEAN")
    private Boolean isAdjusted;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private List<Result> results = new ArrayList<>();


    private String status;

    @JsonProperty("request_id")
    @Column(name = "request_id")
    private String requestId;

    private Integer count;
}
