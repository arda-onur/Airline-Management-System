package com.lydiatech.casestudy.logging;

import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.request.FlightRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.query.Page;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FlightAspectLogging {


    @Before("execution(* com.lydiatech.casestudy.service.FlightService.createFlight(..)) && args(req)")
    public void beforeCreateFlight(FlightRequest req) {
        log.info("Creating Flight with ICAO: {}, FlightNumber: {}, Origin: {}, Departure: {}",
                req.getIcaoCode(), req.getFlightNumber(), req.getOrigin(), req.getDepartureTime());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.createFlight(..))",
            returning = "msg"
    )
    public void afterCreateFlight(String msg) {
        log.info("Flight Created Successfully with {}", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.createFlight(..))",
            throwing = "ex"
    )
    public void onCreateFlightError(Exception ex) {
        log.info("Failed to Create Flight with: {}", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.FlightService.getFlightsByAirline(..)) && args(pageable, airline)")
    public void beforeGetFlightsByAirline(Object pageable, String airline) {
        log.info("Fetching Flights for Airline with: {}", airline);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.getFlightsByAirline(..))",
            returning = "page"
    )
    public void afterGetFlightsByAirline(Page page) {
        log.info("Fetched  Flights");
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.getFlightsByAirline(..))",
            throwing = "ex"
    )
    public void onGetFlightsByAirlineError(Exception ex) {
        log.info("Failed to Fetch Flights with: {}", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.FlightService.deleteFlight(..)) && args(id)")
    public void beforeDeleteFlight(long id) {
        log.info("Deleting Flight with: ID: {}", id);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.deleteFlight(..))",
            returning = "msg"
    )
    public void afterDeleteFlight(String msg) {
        log.info("Flight Deleted Successfully with: {}", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.deleteFlight(..))",
            throwing = "ex"
    )
    public void onDeleteFlightError(Exception ex) {
        log.info("Failed to Delete Flight with: {}", ex.getMessage());
    }

    @Before("execution(* com.lydiatech.casestudy.service.FlightService.updateFlight(..)) && args(req)")
    public void beforeUpdateFlight(FlightRequest req) {
        log.info("Updating Flight with: FlightNumber: {}, ICAO: {}", req.getFlightNumber(), req.getIcaoCode());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.updateFlight(..))",
            returning = "msg"
    )
    public void afterUpdateFlight(String msg) {
        log.info("Flight Updated Successfully with: {}", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.updateFlight(..))",
            throwing = "ex"
    )
    public void onUpdateFlightError(Exception ex) {
        log.info("Failed to Update Flight with: {}", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.FlightService.checkIfThereAreFlightsWithPassenger(..)) && args(icao)")
    public void beforeCheckFlightsWithPassenger(String icao) {
        log.info("Checking Flights with Passengers with:  ICAO: {}", icao);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.checkIfThereAreFlightsWithPassenger(..))",
            returning = "exists"
    )
    public void afterCheckFlightsWithPassenger(boolean exists) {
        log.info("Check Completed with: FlightsWithPassengers: {}", exists);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.FlightService.checkIfThereAreFlightsWithPassenger(..))",
            throwing = "ex"
    )
    public void onCheckFlightsWithPassengerError(Exception ex) {
        log.info("Failed to Check Flights with Passengers -> {}", ex.getMessage());
    }

}
