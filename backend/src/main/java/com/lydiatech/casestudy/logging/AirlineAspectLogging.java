package com.lydiatech.casestudy.logging;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.request.AirlineRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AirlineAspectLogging {


    @Before("execution(* com.lydiatech.casestudy.service.AirlineService.createAirline(..)) && args(airline)")
    public void beforeCreateAirline(Airline airline) {
        log.info("Creating Airline with name='{}', icao='{}'", airline.getName(), airline.getIcaoCode());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.createAirline(..))",
            returning = "created"
    )
    public void afterCreateAirline(Airline created) {
        log.info("Creating Airline created with  id={}, name='{}', icao='{}'",
                created.getId(), created.getName(), created.getIcaoCode());
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.createAirline(..))",
            throwing = "ex"
    )
    public void onCreateAirlineError(Exception ex) {
        log.info("Creating Airline failed: {}", ex.getMessage());
    }


    @Before("execution(* com.lydiatech.casestudy.service.AirlineService.updateAirline(..)) && args(req)")
    public void beforeUpdateAirline(AirlineRequest req) {
        log.info("Updating Airline with icao='{}'", req.getIcaoCode());
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.updateAirline(..))",
            returning = "msg"
    )
    public void afterUpdateAirline(String msg) {
        log.info("Updating Airline success: {}", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.updateAirline(..))",
            throwing = "ex"
    )
    public void onUpdateAirlineError(Exception ex) {
        log.info("Updating Airline failed: {}", ex.getMessage());
    }

    @Before("execution(* com.lydiatech.casestudy.service.AirlineService.deleteAirline(..)) && args(icao)")
    public void beforeDeleteAirline(String icao) {
        log.info("Deleting Airline with icao='{}'", icao);
    }

    @AfterReturning(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.deleteAirline(..))",
            returning = "msg"
    )
    public void afterDeleteAirline(String msg) {
        log.info("Deleting Airline success: {}", msg);
    }

    @AfterThrowing(
            pointcut = "execution(* com.lydiatech.casestudy.service.AirlineService.deleteAirline(..))",
            throwing = "ex"
    )
    public void onDeleteAirlineError(Exception ex) {
        log.info("Deleting Airline failed: {}", ex.getMessage());
    }

}

