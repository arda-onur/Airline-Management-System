package com.lydiatech.casestudy.service;

import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.request.FlightRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
   String  createFlight(FlightRequest flightRequest);
   Page<Flight> getFlightsByAirline(Pageable pageable, String airline);
   String deleteFlight(long id);
   boolean checkIfThereAreFlightsWithPassenger(long id);
   boolean checkIfThereAreFlightsWithPassenger(String icaoCode);
   String updateFlight(FlightRequest flightRequest);
   boolean checkIfThereAreFlightsWithId(long id);
   Page<Flight> getAllFlights(Pageable pageable);
}
