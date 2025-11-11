package com.lydiatech.casestudy.mapper;

import com.lydiatech.casestudy.model.Flight;
import com.lydiatech.casestudy.request.FlightRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfiguration.class)
@Component
public interface FlightMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "airline", ignore = true)
    Flight map (FlightRequest flightRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Flight flight, FlightRequest flightRequest);
}
