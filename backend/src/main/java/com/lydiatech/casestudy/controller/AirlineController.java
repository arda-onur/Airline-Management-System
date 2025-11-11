package com.lydiatech.casestudy.controller;

import com.lydiatech.casestudy.mapper.AirlineMapper;
import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.request.AirlineRequest;
import com.lydiatech.casestudy.service.AirlineService;
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
@RequestMapping("/airline")
@CrossOrigin(origins = "http://localhost:5173")
public class AirlineController {

      private final AirlineMapper airlineMapper;
      private final AirlineService airlineService;

    @PostMapping("/create")
    public ResponseEntity<String > createAirline(@Valid @RequestBody AirlineRequest airlineRequest) {
    System.out.println(airlineRequest.getIcaoCode() + " "+ airlineRequest.getIataCode());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.airlineService.createAirline(this.airlineMapper.map(airlineRequest)));
    }

      @GetMapping("/getAllAirlines")
    public ResponseEntity<Page<Airline>> getAllAirlines(@RequestParam int page, @RequestParam int size) {
     Pageable pageable = PageRequest.of(page, size);
     return ResponseEntity.status(HttpStatus.OK).body(this.airlineService.getAllAirlines(pageable));
    }

    @GetMapping("/getRelatedAirline")
    public ResponseEntity<Page<Airline>> getRelatedAirlines(@RequestParam int page, @RequestParam int size,@RequestParam String icaoCode){
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.status(HttpStatus.OK).body(this.airlineService.getRelatedAirline(icaoCode,pageable));
    }
    @PatchMapping("/update")
    public ResponseEntity updateAirline(@RequestBody AirlineRequest airlineRequest){
    return ResponseEntity.status(HttpStatus.OK).body(this.airlineService.updateAirline(airlineRequest));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAirline(@RequestParam String icaoCode){
    return ResponseEntity.status(HttpStatus.OK).body(this.airlineService.deleteAirline(icaoCode));
    }
}
