package io.zenwave360.example.delivery.core.outbound.events;

import io.zenwave360.example.delivery.core.domain.*;
import io.zenwave360.example.delivery.core.domain.events.*;

public interface EventPublisher {

    void onDeliveryStatusUpdated(DeliveryStatusUpdated event);

}
