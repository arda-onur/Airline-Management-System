package com.lydiatech.casestudy.service;

import com.lydiatech.casestudy.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PassengerService {

    String registerPassenger(Passenger passenger);
    Page<Passenger> getAllPassenger(Pageable pageable);


}
