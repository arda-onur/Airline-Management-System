package com.lydiatech.casestudy.service;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.request.AirlineRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface AirlineService {
    String createAirline(Airline airline);
    String deleteAirline(String icaoCode);

    String updateAirline(AirlineRequest airlineRequest);
    Page<Airline> getAllAirlines(Pageable pageable);

     Page<Airline> getRelatedAirline(String icaoCode, Pageable pageable);


}
