package com.lydiatech.casestudy.model;

import com.lydiatech.casestudy.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "passenger_id", nullable = false )
    private Passenger passenger;
    private String seatNumber;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
    private double price;
    private Date creationDate;
}
