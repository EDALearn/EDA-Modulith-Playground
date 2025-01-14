package io.zenwave360.example.delivery.infrastructure.events;

import io.zenwave360.example.delivery.core.domain.*;
import io.zenwave360.example.delivery.core.domain.events.*;
import io.zenwave360.example.delivery.core.outbound.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("deliveryEventPublisher")
@lombok.RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void onDeliveryStatusUpdated(DeliveryStatusUpdated event) {
        applicationEventPublisher.publishEvent(event);
    }

}
