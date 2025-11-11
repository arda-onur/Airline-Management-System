package com.lydiatech.casestudy.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PassengerRequest implements Serializable {
    @NotBlank(message = "passenger.email.validation.not.blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "passenger.name.validation.not.blank")
    @Size(min=2)
    private String name;

    @Size(min=2)
    @NotBlank(message = "passenger.surname.validation.not.blank")
    private String surname;

}
