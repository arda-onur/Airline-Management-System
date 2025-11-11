package com.lydiatech.casestudy.controller;

import com.lydiatech.casestudy.mapper.PassengerMapper;
import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.request.PassengerRequest;
import com.lydiatech.casestudy.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PassengerController {

    private final PassengerMapper passengerMapper;
    private final PassengerService passengerService;


    @PostMapping("/create")
    public ResponseEntity<String> registerPassenger(@RequestBody @Valid PassengerRequest passengerRequest){
      return ResponseEntity.status(HttpStatus.CREATED)
              .body(this.passengerService.registerPassenger(this.passengerMapper.map(passengerRequest)));
    }

    @GetMapping("/getAllPassenger")
    public ResponseEntity<Page<Passenger>> getAllPassenger(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.passengerService.getAllPassenger(pageable));
    }

}
