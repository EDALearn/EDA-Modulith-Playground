package io.zenwave360.example.restaurants.core.outbound.events;

import io.zenwave360.example.restaurants.core.domain.*;
import io.zenwave360.example.restaurants.core.domain.events.*;

public interface EventPublisher {

    void onKitchenOrderStatusUpdated(KitchenOrderStatusUpdated event);

    void onRestaurantEvent(RestaurantEvent event);

}
