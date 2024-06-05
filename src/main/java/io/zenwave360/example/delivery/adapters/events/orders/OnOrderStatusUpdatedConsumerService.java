package io.zenwave360.example.delivery.adapters.events.orders;

import io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("delivery_OnOrderStatusUpdatedConsumerService")
public class OnOrderStatusUpdatedConsumerService extends AbstractBaseConsumer {

    @Async
    @TransactionalEventListener
    public void onOrderStatusUpdated(OrderStatusUpdated payload) {
        log.debug("Received command request for onOrderStatusUpdated: {}", payload);
        deliveryService.onOrderStatusUpdated(mapper.orderStatusUpdated(payload));
    };

}
