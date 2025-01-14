package io.zenwave360.example.restaurants.infrastructure.events;

import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.domain.events.*;
import io.zenwave360.example.restaurants.core.outbound.events.EventPublisher;
import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements EventPublisher {

    private final List<Object> events = new ArrayList<>();

    public List<Object> getEvents() {
        return events;
    }

    public void onKitchenOrderStatusUpdated(KitchenOrderStatusUpdated event) {
        events.add(event);
    }

    public void onRestaurantEvent(RestaurantEvent event) {
        events.add(event);
    }

}
