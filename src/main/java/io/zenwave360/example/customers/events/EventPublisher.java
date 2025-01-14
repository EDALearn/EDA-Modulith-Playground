package io.zenwave360.example.customers.events;

import io.zenwave360.example.customers.model.*;

public interface EventPublisher {

    void onCustomerEvent(CustomerEvent event);

    void onCustomerAddressUpdated(CustomerAddressUpdated event);

}
