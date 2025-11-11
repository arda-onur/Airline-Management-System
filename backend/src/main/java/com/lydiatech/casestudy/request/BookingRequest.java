package com.lydiatech.casestudy.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequest implements Serializable {

    private long flightId;
    @NotBlank
    private String email;
    @NotBlank
    private String seatNumber;

}
