package io.zenwave360.example.customers.events;

import io.zenwave360.example.customers.model.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component("customersEventPublisher")
@lombok.RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void onCustomerEvent(CustomerEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void onCustomerAddressUpdated(CustomerAddressUpdated event) {
        applicationEventPublisher.publishEvent(event);
    }

}
