package com.lydiatech.casestudy.service.impl;

import com.lydiatech.casestudy.enums.BookingStatus;
import com.lydiatech.casestudy.exception.*;
import com.lydiatech.casestudy.model.Booking;
import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.repository.BookingRepository;
import com.lydiatech.casestudy.repository.FlightRepository;
import com.lydiatech.casestudy.repository.PassengerRepository;
import com.lydiatech.casestudy.request.BookingRequest;
import com.lydiatech.casestudy.service.BookingService;
import com.lydiatech.casestudy.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final NotificationService notificationService;


    @Override
    @Transactional
    public String booking(BookingRequest bookingRequest) {
        Passenger passenger = this.passengerRepository.findByEmail(bookingRequest.getEmail()).orElseThrow(
                () -> new ThereIsNoSuchPassengerException("There is no Passenger with this email"));

        Flight flight = this.flightRepository.findById(bookingRequest.getFlightId()).orElseThrow(
                () -> new ThereIsNoSuchFlightException("There is no Flight with this email"));


        if (flight.getDepartureTime().before(new Date())) {
            throw new FlightDateExpiredExcepiton("Flight Date Expired");
        }

        boolean exists = this.bookingRepository.existsBookingByPassengerAndFlight_Id(passenger,
                                                                                        bookingRequest.getFlightId());
        if (exists) {
            throw new PassengerAlreadyExistsException("Passenger already has a booking for this flight");
        }

        int overbookedCapacity =  flight.getCapacity()+ (int) (flight.getCapacity() * 0.1);

        if(flight.getBookedSeats() == overbookedCapacity) {
            throw new BookingCannotCreateException("Booking is capacity over the booking limit");
        }

        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setSeatNumber(bookingRequest.getSeatNumber());
        booking.setPrice(applyDiscountOrNormalPrice(flight.getBasePrice(), passenger.getLoyaltyPoints()).doubleValue());
        booking.setCreationDate(new Date());
        booking.setPrice(flight.getBasePrice().doubleValue());


        if (flight.getBookedSeats() <= flight.getCapacity()) {
            booking.setBookingStatus(BookingStatus.CONFIRMED);
            passenger.setLoyaltyPoints((int) (passenger.getLoyaltyPoints() + (flight.getBasePrice().doubleValue() * 0.1)));
            registerSyncForAsyncBookingEmailConfirmation(passenger.getEmail(), flight.getFlightNumber());
        } else {
            booking.setBookingStatus(BookingStatus.WAITLISTED);
        }

        this.bookingRepository.save(booking);
        flight.setBasePrice(calculateDynamicPricing(flight.getBasePrice(), flight.getCapacity(),
                                                                    flight.getBookedSeats(),overbookedCapacity,flight));
        return "Reservation created with booking id : " + booking.getId();
    }

    @Override
    public BigDecimal calculateDynamicPricing(
            BigDecimal baseprice, int totalSeat, int bookedSeat,int overbookedCapacity,Flight flight) {

        double rateOfOccupyingSeats = (double) bookedSeat / totalSeat;
        BigDecimal price = baseprice;
        if (rateOfOccupyingSeats < 0.5 || flight.getBookedSeats() == overbookedCapacity ) {
            price = baseprice;
        } else if (rateOfOccupyingSeats <= 0.8) {
            price = baseprice.multiply(BigDecimal.valueOf(1.2));
        } else {
            price = baseprice.multiply(BigDecimal.valueOf(1.5));
        }

        return price;
    }


    @Override
    public String overBooking(Flight flightId, Passenger passenger,Flight flight) {
        Booking waitlistedBooking = this.bookingRepository
                .findFirstByFlight_IdAndBookingStatusOrderByCreationDateAsc(flightId.getId(), BookingStatus.WAITLISTED);

        if (waitlistedBooking != null){
            waitlistedBooking.setBookingStatus(BookingStatus.CONFIRMED);
            waitlistedBooking.getPassenger().setLoyaltyPoints(
               (int) (waitlistedBooking.getPassenger().getLoyaltyPoints() + (flight.getBasePrice().doubleValue() * 0.1)));

            registerSyncForAsyncBookingEmailConfirmation(
                                                waitlistedBooking.getPassenger().getEmail(), flight.getFlightNumber());
            bookingRepository.save(waitlistedBooking);
        }else {
            flightId.setBookedSeats(flightId.getBookedSeats() - 1);
            flightRepository.save(flightId);
            return "";
        }
        return "Waitlisted passenger status changed to Confirmed: " + waitlistedBooking.getPassenger().getEmail() ;
    }

    @Override
    public String cancelBooking(int bookingId) {
        Booking booking = this.bookingRepository.findById(bookingId).orElse(null);
        if (booking == null){
            throw new ThereIsNoSuchBookingException("There is no booking with this id");
        }
        Flight flightId = booking.getFlight();

        if (booking == null)
            throw new ThereIsNoSuchBookingException("There is no booking with this id"+bookingId);

        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.getPassenger().setLoyaltyPoints(booking.getPassenger().getLoyaltyPoints() - 200);
        bookingRepository.save(booking);
        overBooking(flightId,booking.getPassenger(),booking.getFlight());
    return "Booking cancelled with:" + booking.getPassenger().getEmail();
    }

    @Override
    public BigDecimal applyDiscountOrNormalPrice(BigDecimal price, int loyaltyPoint) {
        if (price == null)
            throw new IllegalArgumentException("Price cannot be null");

        if (loyaltyPoint > 1000)
            return price.multiply(BigDecimal.valueOf(0.9));

        return price;
    }

    @Override
    public Page<Booking> getBookingsWithId(long bookingId, Pageable pageable) {
        return this.bookingRepository.findBookingsById(bookingId,pageable);
    }

    @Override
    public Page<Booking> getPassengerBookingsWithId(int passengerId, Pageable pageable) {
        return this.bookingRepository.findBookingsByPassenger_Id(passengerId,pageable);
    }

    @Override
    public Page<Booking> getAllBookings(Pageable pageable) {
        return this.bookingRepository.findAll(pageable);
    }


    void registerSyncForAsyncBookingEmailConfirmation(String email, String flightNo) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                notificationService.sendBookingConfirmationEmail(email, flightNo);
            }
        });
    }
}