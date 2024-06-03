package io.zenwave360.example.customers.mappers;

import io.zenwave360.example.customers.dtos.*;
import io.zenwave360.example.customers.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { BaseMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    io.zenwave360.example.customers.events.dtos.Address asAddress(Address address);

    io.zenwave360.example.customers.events.dtos.CustomerEvent asCustomerEvent(Customer customer);

    io.zenwave360.example.customers.events.dtos.CustomerEvent asCustomerEvent(String id);

    io.zenwave360.example.customers.events.dtos.CustomerAddressUpdated asCustomerAddressUpdated(Customer customer);

}
