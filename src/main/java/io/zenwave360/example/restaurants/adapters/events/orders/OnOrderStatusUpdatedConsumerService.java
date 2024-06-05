package io.zenwave360.example.restaurants.adapters.events.orders;

import io.zenwave360.example.orders.core.domain.events.OrderStatusUpdated;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnOrderStatusUpdatedConsumerService extends AbstractBaseConsumer {

    @Async
    @TransactionalEventListener
    public void onOrderStatusUpdated(OrderStatusUpdated payload) {
        log.debug("Received command request for onOrderStatusUpdated: {}", payload);
        var orderStatusUpdated = new io.zenwave360.example.restaurants.core.inbound.dtos.OrderStatusUpdated()
                .setOrderId(payload.getId())
                .setStatus(payload.getStatus().toString());
        restaurantOrdersService.onOrderStatusUpdated(orderStatusUpdated);
    };

}
