package io.zenwave360.example.customers.events;

import io.zenwave360.example.customers.model.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements EventPublisher {

    private final List<Object> events = new ArrayList<>();

    public List<Object> getEvents() {
        return events;
    }

    public void onCustomerEvent(CustomerEvent event) {
        events.add(event);
    }

    public void onCustomerAddressUpdated(CustomerAddressUpdated event) {
        events.add(event);
    }

}
