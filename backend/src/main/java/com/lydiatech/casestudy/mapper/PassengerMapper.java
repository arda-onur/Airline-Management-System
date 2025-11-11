package com.lydiatech.casestudy.mapper;

import com.lydiatech.casestudy.model.Passenger;
import com.lydiatech.casestudy.request.PassengerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfiguration.class)
@Component
public interface PassengerMapper {
    @Mapping(target = "id", ignore = true)
    Passenger map(PassengerRequest passengerRequest);
}
