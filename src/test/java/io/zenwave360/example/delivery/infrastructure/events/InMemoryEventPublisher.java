package io.zenwave360.example.delivery.infrastructure.events;

import io.zenwave360.example.delivery.core.domain.*;
import io.zenwave360.example.delivery.core.domain.events.*;
import io.zenwave360.example.delivery.core.outbound.events.EventPublisher;
import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements EventPublisher {

    private final List<Object> events = new ArrayList<>();

    public List<Object> getEvents() {
        return events;
    }

    public void onDeliveryStatusUpdated(DeliveryStatusUpdated event) {
        events.add(event);
    }

}
