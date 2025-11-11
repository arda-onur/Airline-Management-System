package com.lydiatech.casestudy.repository;

import com.lydiatech.casestudy.model.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    Optional<Airline> findByIcaoCode(String icaoCode);

    Page<Airline> findAirlineByIcaoCode(String icaoCode, Pageable pageable);

    Airline findAirlineByName(String name);

    void deleteByIcaoCode(String icaoCode);

    boolean existsAirlineByNameAndIcaoCode(String name, String icaoCode);
}
