package io.zenwave360.example.orders.infrastructure.events;

import io.zenwave360.example.orders.core.domain.events.*;
import io.zenwave360.example.orders.core.outbound.events.EventPublisher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements EventPublisher {

    private final List<Object> events = new ArrayList<>();

    public List<Object> getEvents() {
        return events;
    }

    public void onOrderStatusUpdated(OrderStatusUpdated event) {
        events.add(event);
    }

    public void onOrderEvent(OrderEvent event) {
        events.add(event);
    }

}
