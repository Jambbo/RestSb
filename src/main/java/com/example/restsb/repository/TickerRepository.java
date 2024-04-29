package com.example.restsb.repository;

import com.example.restsb.domain.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<Ticker,Long> {

    Ticker findByName(String name);

}
