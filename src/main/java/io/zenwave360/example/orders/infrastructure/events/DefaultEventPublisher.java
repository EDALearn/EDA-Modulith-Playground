package io.zenwave360.example.orders.infrastructure.events;

import io.zenwave360.example.orders.core.domain.events.*;
import io.zenwave360.example.orders.core.outbound.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("ordersEventPublisher")
@lombok.RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void onOrderStatusUpdated(OrderStatusUpdated event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void onOrderEvent(OrderEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
