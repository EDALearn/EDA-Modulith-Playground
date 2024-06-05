package io.zenwave360.example.orders.core.implementation.mappers;

import io.zenwave360.example.orders.core.domain.*;
import io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated;
import io.zenwave360.example.orders.core.inbound.dtos.*;
import org.apache.commons.lang3.ObjectUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.OffsetDateTime;

@Mapper(uses = { BaseMapper.class })
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    default OrderStatusUpdated asOrderStatusUpdated(CustomerOrder customerOrder, OrderStatus previousStatus) {
        return new OrderStatusUpdated() //
                .setId(customerOrder.getId())
                .setDateTime(Instant.now())
                .setStatus(customerOrder.getStatus())
                .setPreviousStatus(previousStatus);
    }

    @ValueMapping(source = "REJECTED", target = "CANCELLED")
    @ValueMapping(source = "ACCEPTED", target = "KITCHEN_ACCEPTED")
    @ValueMapping(source = "IN_PROGRESS", target = "KITCHEN_IN_PROGRESS")
    @ValueMapping(source = "READY", target = "KITCHEN_READY")
    @ValueMapping(source = "DELIVERED", target = "KITCHEN_DELIVERED")
    @ValueMapping(source = "CANCELLED", target = "CANCELLED")
    @ValueMapping(source = MappingConstants.ANY_UNMAPPED, target = MappingConstants.NULL)
    OrderStatus asOrderStatus(KitchenStatus status);

    default OrderStatus asOrderStatus(KitchenStatus kitchenStatus, OrderStatus originalStatus) {
        if(kitchenStatus == KitchenStatus.ACCEPTED && originalStatus == OrderStatus.DELIVERY_ACCEPTED) {
            return OrderStatus.CONFIRMED;
        }
        return ObjectUtils.firstNonNull(asOrderStatus(kitchenStatus), originalStatus);
    }

    @ValueMapping(source = "REJECTED", target = "CANCELLED")
    @ValueMapping(source = "ACCEPTED", target = "DELIVERY_ACCEPTED")
    @ValueMapping(source = "IN_PROGRESS", target = "ON_DELIVERY")
    @ValueMapping(source = "DELIVERED", target = "DELIVERY_ACCEPTED")
    @ValueMapping(source = "CANCELLED", target = "CANCELLED")
    @ValueMapping(source = MappingConstants.ANY_UNMAPPED, target = MappingConstants.NULL)
    OrderStatus asOrderStatus(DeliveryStatus deliveryStatus);

    default OrderStatus asOrderStatus(DeliveryStatus deliveryStatus, OrderStatus originalStatus) {
        if(deliveryStatus == DeliveryStatus.ACCEPTED && originalStatus == OrderStatus.KITCHEN_ACCEPTED) {
            return OrderStatus.CONFIRMED;
        }
        return ObjectUtils.firstNonNull(asOrderStatus(deliveryStatus), originalStatus);
    }

    io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated asOrderStatusUpdated(
            CustomerOrder customerOrder);

    io.zenwave360.example.orders.core.domain.events.OrderEvent asOrderEvent(CustomerOrder customerOrder);

}
