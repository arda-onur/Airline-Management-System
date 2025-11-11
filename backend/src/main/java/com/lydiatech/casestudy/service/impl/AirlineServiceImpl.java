package com.lydiatech.casestudy.service.impl;

import com.lydiatech.casestudy.exception.AirlineExistsException;
import com.lydiatech.casestudy.exception.FlightCannotCreateException;
import com.lydiatech.casestudy.exception.PassengerAlreadyExistsException;
import com.lydiatech.casestudy.exception.ThereisNoSuchAirlineException;
import com.lydiatech.casestudy.mapper.AirlineMapper;
import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.repository.AirlineRepository;
import com.lydiatech.casestudy.request.AirlineRequest;
import com.lydiatech.casestudy.service.AirlineService;
import com.lydiatech.casestudy.service.FlightService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;
    private final FlightService flightService;

    @Override
    public String createAirline(Airline airline) {
        if (this.airlineRepository.existsAirlineByNameAndIcaoCode(airline.getName(),airline.getIcaoCode())) {
            throw new AirlineExistsException("Airline already exists with: "
                                                            + airline.getName() +" : "+airline.getIcaoCode());
        }
        this.airlineRepository.save(airline);
      return "Airline created successfully with: " + airline.getName() +" : "+airline.getIcaoCode();
    }


    @Override
    @Transactional
    public String deleteAirline(String icaoCode) {
        boolean checkIcaoCode = this.airlineRepository.findByIcaoCode(icaoCode).isPresent();
        if (!checkIcaoCode)
            throw new ThereisNoSuchAirlineException("There is no airline with this icao code " + icaoCode);

       if(this.flightService.checkIfThereAreFlightsWithPassenger(icaoCode))
          throw new PassengerAlreadyExistsException("Passenger already exists with icao code " + icaoCode);

      this.airlineRepository.deleteByIcaoCode(icaoCode);
      return "Airline with icao code " + icaoCode + " has been deleted";
    }

    @Override
    public String updateAirline(AirlineRequest airlineRequest) {
        Airline airline = this.airlineRepository.findByIcaoCode(airlineRequest.getIcaoCode()).orElse(null);

        if(airline == null) {
           throw new ThereisNoSuchAirlineException("There is no airline with this icao code");
       }
        this.airlineMapper.update(airline, airlineRequest);
        this.airlineRepository.save(airline);
        return "Update successful with: " + airline.getIcaoCode();
    }

    @Override
    public Page<Airline> getAllAirlines(Pageable pageable) {
        return this.airlineRepository.findAll(pageable);
    }

    @Override
    public Page<Airline> getRelatedAirline(String icaoCode,Pageable pageable) {
        return this.airlineRepository.findAirlineByIcaoCode(icaoCode, pageable);
    }


}
