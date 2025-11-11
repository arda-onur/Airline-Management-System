package com.lydiatech.casestudy.repository;

import com.lydiatech.casestudy.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Optional<Passenger> findByEmail(String email);
}
