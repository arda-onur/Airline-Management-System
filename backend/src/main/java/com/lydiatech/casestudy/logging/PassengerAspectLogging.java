package com.lydiatech.casestudy.logging;

import com.lydiatech.casestudy.model.Passenger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
@Aspect
@Component
@Slf4j
public class PassengerAspectLogging {


    @Before("execution(* com.lydiatech.casestudy.service.PassengerService.registerPassenger(..)) && args(passenger)")
    public void beforeRegisterPassenger(Passenger passenger) {
        log.info("Registering Passenger method called for email: {}", passenger.getEmail());
    }


    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.PassengerService.registerPassenger(..))",
            returning = "createdPassenger"
    )
    public void afterRegisterPassenger(Passenger createdPassenger) {
        log.info("Passenger '{}' registered successfully with ID: {}",
                createdPassenger.getEmail(), createdPassenger.getId());
    }


    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.PassengerService.registerPassenger(..))",
            throwing = "ex"
    )
    public void afterThrowingRegisterPassenger(Exception ex) {
        log.info("Passenger registration failed! Reason: {}", ex.getMessage());
    }

}
