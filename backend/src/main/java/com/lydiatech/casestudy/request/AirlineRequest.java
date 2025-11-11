package com.lydiatech.casestudy.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AirlineRequest implements Serializable {
    @NotBlank(message = "airline.iatacode.validation.not.blank")
    @Size(min = 2, max = 2)
    private String iataCode;
    @NotBlank(message = "airline.icaocode.validation.not.blank")
    @Size(min = 3, max = 3)
    private String icaoCode;
    @NotBlank(message = "airline.name.validation.not.blank")
    private String name;
    @NotBlank(message = "airline.country.validation.not.blank")
    private String country;
    @NotBlank(message = "airline.fleetsize.validation.not.blank")
    private String fleetSize;
}
