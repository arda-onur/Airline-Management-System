package com.lydiatech.casestudy.service;

import com.lydiatech.casestudy.model.Booking;
import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.request.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.print.Book;
import java.math.BigDecimal;

public interface BookingService {
    BigDecimal calculateDynamicPricing(BigDecimal price, int totalSeat, int BookedSeat,int overbookedCapacity,Flight flight);
    String booking(BookingRequest bookingRequest);
    String overBooking(Flight flightId,Passenger passenger, Flight flight);
    String cancelBooking(int bookingId);
    BigDecimal applyDiscountOrNormalPrice(BigDecimal price, int loyaltyPoint);
    Page<Booking> getBookingsWithId(long bookingId,Pageable pageable);
    Page<Booking> getPassengerBookingsWithId(int passengerId,Pageable pageable);
    Page<Booking> getAllBookings(Pageable pageable);


}
