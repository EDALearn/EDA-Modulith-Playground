package io.zenwave360.example.orders.core.implementation.mappers;

import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.inbound.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { BaseMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated asOrderStatusUpdated(
            CustomerOrder customerOrder);

    io.zenwave360.example.orders.core.domain.events.OrderEvent asOrderEvent(CustomerOrder customerOrder);

}
