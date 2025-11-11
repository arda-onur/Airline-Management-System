package com.lydiatech.casestudy.controller;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.model.Booking;
import com.lydiatech.casestudy.request.BookingRequest;
import com.lydiatech.casestudy.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {
    private final BookingService bookingService;
     @PostMapping("/create")
    public ResponseEntity<String> addBooking(@RequestBody BookingRequest bookingRequest) {
         return ResponseEntity.status(HttpStatus.OK).body(this.bookingService.booking(bookingRequest));
     }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelBooking(@RequestParam int bookingId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.bookingService.cancelBooking(bookingId));
    }

    @GetMapping("/getBookingsWithId")
    public ResponseEntity<Page<Booking>> getBookingsWithId(@RequestParam int page, @RequestParam int size, @RequestParam long id){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(this.bookingService.getBookingsWithId(id, pageable));
    }

    @GetMapping("/getPassengerBookingsWithId")
    public ResponseEntity<Page<Booking>> getBookingsWithId(@RequestParam int page, @RequestParam int size, @RequestParam int id){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(this.bookingService.getPassengerBookingsWithId(id, pageable));
    }

    @GetMapping("getAllBookings")
    public ResponseEntity<Page<Booking>> getBookingsWithId(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(this.bookingService.getAllBookings(pageable));
    }
}
