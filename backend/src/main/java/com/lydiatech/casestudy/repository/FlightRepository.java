package com.lydiatech.casestudy.repository;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> getFlightsByAirline(Airline foundedAirline, Pageable pageable);

    boolean existsByAirline_IcaoCodeAndFlightNumberAndDepartureTimeLessThanAndArrivalTimeGreaterThan(String airlineİcaoCode, String flightNumber, Date departureTimeIsLessThan, Date arrivalTimeIsGreaterThan);

    boolean existsByIdAndBookedSeatsGreaterThan(long id, int bookedSeatsIsGreaterThan);

    boolean existsFlightByAirline_IcaoCodeAndBookedSeatsGreaterThan(String airlineİcaoCode, int bookedSeatsIsGreaterThan);

    boolean existsByAirline_IcaoCodeAndOriginAndDepartureTimeBetween(String airlineİcaoCode, String origin, Date departureTimeAfter, Date departureTimeBefore);

    Flight findByFlightNumber(String flightNumber);

    boolean existsFlightByIdAndBookedSeatsGreaterThan(long id, int bookedSeatsIsGreaterThan);

    boolean existsFlightById(long id);
}
