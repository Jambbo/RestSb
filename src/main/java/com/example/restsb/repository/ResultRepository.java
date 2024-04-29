package com.example.restsb.repository;

import com.example.restsb.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

    @Query("SELECT r FROM Result r JOIN r.stock s WHERE r.time = :time AND s.ticker = :ticker")
    Optional<Result> findByTimeAndTicker(Long time, String ticker);

}
