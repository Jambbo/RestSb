package com.example.restsb.repository;

import com.example.restsb.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result,Long> {
    @Query("SELECT r FROM Result r JOIN Stock s ON r.stock=s WHERE r.time= :time AND s.ticker= :ticker")
    Optional<Result> findByTimeAndTicker(@Param("time") Long time, @Param("ticker") String ticker);

    @Query("SELECT r FROM Result r JOIN Stock s ON r.stock=s WHERE r.time> :startTime AND r.time< :endTime AND s.ticker= :ticker")
    List<Result> findByTimeRangeAndTicker(Long startTime, Long endTime, String ticker);
}
