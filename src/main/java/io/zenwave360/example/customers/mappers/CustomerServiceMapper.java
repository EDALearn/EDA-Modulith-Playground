package io.zenwave360.example.customers.mappers;

import io.zenwave360.example.customers.dtos.*;
import io.zenwave360.example.customers.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { BaseMapper.class })
public interface CustomerServiceMapper {

    CustomerServiceMapper INSTANCE = Mappers.getMapper(CustomerServiceMapper.class);

    @Mapping(target = "id", ignore = true)
    Customer update(@MappingTarget Customer entity, Customer input);
    // Customer asCustomer(String identifier, Address address);

    @Mapping(target = "id", ignore = true)
    Customer update(@MappingTarget Customer entity, String identifier, Address address);

}
