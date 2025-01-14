package io.zenwave360.example.orders.core.outbound.events;

import io.zenwave360.example.orders.core.domain.events.*;

public interface EventPublisher {

    void onOrderStatusUpdated(OrderStatusUpdated event);

    void onOrderEvent(OrderEvent event);

}
