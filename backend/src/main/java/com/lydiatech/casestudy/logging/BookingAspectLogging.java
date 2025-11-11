package com.lydiatech.casestudy.logging;

import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.request.BookingRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Aspect
public class BookingAspectLogging {
    
    @Before("execution(* com.lydiatech.casestudy.service.BookingService.booking(..)) && args(req)")
    public void beforeCreateBooking(BookingRequest req) {
        log.info("Creating booking with email={}, flightId={}, seat={}",
                req.getEmail(), req.getFlightId(), req.getSeatNumber());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.booking(..))",
            returning = "msg"
    )
    public void afterCreateBooking(String msg) {
        log.info("Booking created successfully with message='{}'", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.booking(..))",
            throwing = "ex"
    )
    public void onCreateBookingFailure(Exception ex) {
        log.info("Failed to create booking with reason='{}'", ex.getMessage());
    }

    @Before("execution(* com.lydiatech.casestudy.service.BookingService.cancelBooking(..)) && args(id)")
    public void beforeCancelBooking(int id) {
        log.info("Cancelling booking with id={}", id);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.cancelBooking(..))",
            returning = "msg"
    )
    public void afterCancelBooking(String msg) {
        log.info("Booking cancelled successfully with message='{}'", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.cancelBooking(..))",
            throwing = "ex"
    )
    public void onCancelBookingFailure(Exception ex) {
        log.info("Failed to cancel booking with reason='{}'", ex.getMessage());
    }

    @Before("execution(* com.lydiatech.casestudy.service.BookingService.overBooking(..)) && args(flightId, passenger, flight)")
    public void beforeOverBooking(Flight flightId, Passenger passenger, Flight flight) {
        log.info("Processing overbooking with flightId={}, flightNo={}, passengerEmail={}",
                flightId.getId(), flight.getFlightNumber() ,passenger.getEmail());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.overBooking(..))",
            returning = "msg"
    )
    public void afterOverBooking(String msg) {
        log.info("Overbooking processed successfully with message='{}'", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.overBooking(..))",
            throwing = "ex"
    )
    public void onOverBookingFailure(Exception ex) {
        log.info("Failed to process overbooking with reason='{}'", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.BookingService.calculateDynamicPricing(..)) && args(basePrice, totalSeat, bookedSeat, overbookedCapacity, flight)")
    public void beforeCalcPrice(BigDecimal basePrice, int totalSeat, int bookedSeat, int overbookedCapacity, Flight flight) {
        log.info("Calculating dynamic price with base={}, totalSeat={}, bookedSeat={}, overbookedCap={}, flightNo={}",
                basePrice, totalSeat, bookedSeat, overbookedCapacity, flight.getFlightNumber());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.calculateDynamicPricing(..))",
            returning = "price"
    )
    public void afterCalcPrice(BigDecimal price) {
        log.info("Dynamic price calculated with result={}", price);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.calculateDynamicPricing(..))",
            throwing = "ex"
    )
    public void onCalcPriceFailure(Exception ex) {
        log.info("Failed to calculate dynamic price with reason='{}'", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.BookingService.applyDiscountOrNormalPrice(..)) && args(price, loyalty)")
    public void beforeApplyDiscount(BigDecimal price, int loyalty) {
        log.info("Applying discount with price={}, loyaltyPoints={}", price, loyalty);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.applyDiscountOrNormalPrice(..))",
            returning = "result"
    )
    public void afterApplyDiscount(BigDecimal result) {
        log.info("Discount applied with resultPrice={}", result.doubleValue());
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.BookingService.applyDiscountOrNormalPrice(..))",
            throwing = "ex"
    )
    public void onApplyDiscountFailure(Exception ex) {
        log.info("Failed to apply discount with reason='{}'", ex.getMessage());
    }



}
