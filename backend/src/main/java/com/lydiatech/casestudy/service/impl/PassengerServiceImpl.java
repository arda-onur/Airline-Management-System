package com.lydiatech.casestudy.service.impl;

import com.lydiatech.casestudy.exception.PassengerAlreadyExistsException;
import com.lydiatech.casestudy.mapper.PassengerMapper;
import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.repository.FlightRepository;
import com.lydiatech.casestudy.repository.PassengerRepository;
import com.lydiatech.casestudy.service.FlightService;
import com.lydiatech.casestudy.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

   private final PassengerRepository passengerRepository;


    @Override
    public String registerPassenger(Passenger passenger) {
     this.passengerRepository.findByEmail(passenger.getEmail())
             .ifPresent(e -> {throw new PassengerAlreadyExistsException(
                             "Passenger Already exists with: " +passenger.getEmail());});
     passenger.setLoyaltyPoints(0);
     this.passengerRepository.save(passenger);
     return "Passenger Registered Successfully: " + passenger.getEmail();
    }

 @Override
 public Page<Passenger> getAllPassenger(Pageable pageable) {
  return this.passengerRepository.findAll(pageable);
 }


}
