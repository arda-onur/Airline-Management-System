package com.lydiatech.casestudy.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lydiatech.casestudy.model.Airline;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class FlightRequest implements Serializable {

    @NotBlank(message = "flight.icaoCode.validation.not.blank")
    @Size(min = 3, max = 3)
    private String icaoCode;

    @NotBlank(message = "flight.flightNumber.validation.not.blank")
    @Size(min = 1, max = 10)
    private String flightNumber;

    @NotBlank(message = "flight.origin.validation.not.blank")
    private String origin;

    @NotBlank(message = "flight.destination.validation.not.blank")
    private String destination;

    @NotNull(message = "flight.departureTime.validation.not.null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Istanbul")
    private Date departureTime;

    @NotNull(message = "flight.arrivalTime.validation.not.null")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Europe/Istanbul")
    private Date arrivalTime;

    @NotNull(message = "flight.basePrice.validation.not.null")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal basePrice;

    @Min(value = 50, message="flight.capacity.validation")
    @Max(value = 400 , message="flight.capacity.validation")
    private Integer capacity;

    @Min(value = 0)
    private Integer bookedSeats;
}
