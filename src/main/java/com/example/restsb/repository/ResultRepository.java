package com.example.restsb.repository;

import com.example.restsb.domain.Result;
import com.example.restsb.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

    @Query(value = "SELECT * FROM results WHERE stock_id = :stockId AND time IN :timestamps", nativeQuery = true)
    List<Result> findAllByStockAndTIn(@Param("stockId") Long stockId, @Param("timestamps") List<Long> timestamps);}
