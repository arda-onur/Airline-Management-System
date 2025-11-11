package com.lydiatech.casestudy.mapper;

import com.lydiatech.casestudy.model.Airline;
import com.lydiatech.casestudy.request.AirlineRequest;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(config = MapperConfiguration.class)
@Component
public interface AirlineMapper {
    @Mapping(target = "id", ignore = true)
   Airline map(AirlineRequest airlineRequest);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Airline target, AirlineRequest source);


}





