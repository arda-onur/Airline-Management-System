package com.lydiatech.casestudy.controller;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.request.AirlineRequest;
import com.lydiatech.casestudy.request.FlightRequest;
import com.lydiatech.casestudy.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<String> createFlight(@Valid @RequestBody FlightRequest flightRequest) {
      return ResponseEntity.status(HttpStatus.OK).body(this.flightService.createFlight(flightRequest));
    }

    @GetMapping("/getFlightsByAirlines")
    public Page<Flight> getRelatedFlights(@RequestParam int page, @RequestParam int size, @RequestParam String airline){
        Pageable pageable = PageRequest.of(page, size);
        return this.flightService.getFlightsByAirline(pageable,airline);
    }
    @GetMapping("/getAllFlights")
    public Page<Flight> getRelatedFlights(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.flightService.getAllFlights(pageable);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFlight(@RequestParam long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.deleteFlight(id));
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateFlight(@RequestBody FlightRequest flightRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.flightService.updateFlight(flightRequest));
    }
}
