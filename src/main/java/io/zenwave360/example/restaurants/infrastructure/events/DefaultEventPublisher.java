package io.zenwave360.example.restaurants.infrastructure.events;

import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.domain.events.*;
import io.zenwave360.example.restaurants.core.outbound.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("restaurantsEventPublisher")
@lombok.RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void onKitchenOrderStatusUpdated(KitchenOrderStatusUpdated event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void onRestaurantEvent(RestaurantEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
