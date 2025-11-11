package com.lydiatech.casestudy.service.impl;

import com.lydiatech.casestudy.exception.*;
import com.lydiatech.casestudy.mapper.FlightMapper;
import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.repository.AirlineRepository;
import com.lydiatech.casestudy.repository.BookingRepository;
import com.lydiatech.casestudy.repository.FlightRepository;
import com.lydiatech.casestudy.request.FlightRequest;
import com.lydiatech.casestudy.service.FlightService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirlineRepository airlineRepository;
    private final BookingRepository bookingRepository;

    @Override
    public String createFlight(FlightRequest flightRequest) {
        Airline airline = this.airlineRepository.findByIcaoCode(flightRequest.getIcaoCode())
                .orElseThrow(() -> new ThereisNoSuchAirlineException("No Such Airline"));

        boolean sameDaySameDestSameFN = checkDayforFlight(
                flightRequest.getDepartureTime(),airline.getIcaoCode(),flightRequest.getOrigin());

                if(sameDaySameDestSameFN){
                    throw new FlightCannotCreateException(
                            "This flight number already exists for this airline and origin on the same day.");
                }

        boolean overlap = this.overlappingControl(
                flightRequest.getIcaoCode(),flightRequest.getFlightNumber(),
                flightRequest.getDepartureTime(),flightRequest.getArrivalTime());
                if(overlap){
                    throw new FlightCannotCreateException("Overlapping flight exists for this airline and flight number.");
                }

        Flight flight = this.flightMapper.map(flightRequest);
        flight.setAirline(airline);

       this.flightRepository.save(flight);
        return "Flight created successfully";
    }

    @Override
    public Page<Flight> getFlightsByAirline(Pageable pageable, String airline) {
        Airline foundedAirline = this.airlineRepository.findAirlineByName(airline);

        return this.flightRepository.getFlightsByAirline(foundedAirline,pageable);
    }

    @Override
    @Transactional
    public String deleteFlight(long id) {
        if (bookingRepository.existsBookingsByFlight_Id((id))) {
            throw new FlightHasBookingsException("This flight has bookings; delete/cancel them first.");
        }
        if(!checkIfThereAreFlightsWithId(id)){
            throw new ThereIsNoSuchFlightException("No Such Flight exists for this id: " + id);
        }
        if(checkIfThereAreFlightsWithPassenger(id)){
            throw new PassengerAlreadyExistsException("Passengers Already Exist with this id: " + id);
        }
        this.flightRepository.deleteById(id);
        return "Flight deleted successfully";
    }

    @Override
    public boolean checkIfThereAreFlightsWithPassenger(String icaoCode) {
        return this.flightRepository.
                existsFlightByAirline_IcaoCodeAndBookedSeatsGreaterThan(icaoCode,0);
    }


    @Override
    public boolean checkIfThereAreFlightsWithPassenger(long id) {
        boolean exists = this.flightRepository.existsFlightByIdAndBookedSeatsGreaterThan(id,0);
        return exists;
    }


    @Override
    public String updateFlight(FlightRequest flightRequest) {
        Flight flight = this.flightRepository.findByFlightNumber(flightRequest.getFlightNumber());

        if(flight == null) {
            throw new ThereIsNoSuchFlightException("There is no flight with this flightNumber");
        }

        this.flightMapper.update(flight, flightRequest);




        this.flightRepository.save(flight);
        return "Update successful";
    }

    @Override
    public boolean checkIfThereAreFlightsWithId(long id) {
        boolean exists = this.flightRepository.existsFlightById((id));
        return exists;
    }

    @Override
    public Page<Flight> getAllFlights(Pageable pageable) {
        return this.flightRepository.findAll(pageable);
    }


    private boolean checkIfThereArePassengerRelatedFlight(long id){
        return this.flightRepository.existsByIdAndBookedSeatsGreaterThan(id,0);
    }




    private boolean checkDayforFlight(Date requestDate,String icaoCode,String origin) {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Istanbul"));

        cal.setTime(requestDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dayStart = cal.getTime();


        cal.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDayStart = cal.getTime();

        Date dayEnd = new Date(nextDayStart.getTime() - 1);

        return flightRepository.existsByAirline_IcaoCodeAndOriginAndDepartureTimeBetween(
                icaoCode,
                origin,
                dayStart,
                dayEnd
        );
    }
   private boolean overlappingControl(String icaoCode,String flightNumber,Date departureTime,Date arrivalTime) {
    return
            this.flightRepository
                    .existsByAirline_IcaoCodeAndFlightNumberAndDepartureTimeLessThanAndArrivalTimeGreaterThan(
                            icaoCode,
                            flightNumber,
                            arrivalTime,
                            departureTime
                    );

   }
}
