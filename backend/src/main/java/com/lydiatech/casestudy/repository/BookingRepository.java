package com.lydiatech.casestudy.repository;

import com.lydiatech.casestudy.enums.BookingStatus;
import com.lydiatech.casestudy.model.Booking;
import com.lydiatech.casestudy.model.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


    boolean existsBookingByPassengerAndFlight_Id(Passenger passenger, long flightİd);


    Booking findFirstByFlight_IdAndBookingStatusOrderByCreationDateAsc(long flightİd, BookingStatus bookingStatus);


    void deleteAllByBookingStatusAndCreationDateBetween(BookingStatus bookingStatus, Date creationDateAfter, Date creationDateBefore);

    Page<Booking> findBookingsById(long id, Pageable pageable);

    Page<Booking> findBookingsByPassenger_Id(long passengerİd, Pageable pageable);

    boolean existsBookingsByFlight_Id(long flightİd);
}
