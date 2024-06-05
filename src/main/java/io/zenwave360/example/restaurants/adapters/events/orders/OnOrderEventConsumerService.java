package io.zenwave360.example.restaurants.adapters.events.orders;

import io.zenwave360.example.orders.core.domain.events.OrderEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnOrderEventConsumerService extends AbstractBaseConsumer {

    @Async
    @TransactionalEventListener
    public void onOrderEvent(OrderEvent payload) {
        log.debug("Received command request for onOrderEvent: {}", payload);
        var kitchenOrderInput = mapper.asKitchenOrder(payload);
        restaurantOrdersService.createKitchenOrder(kitchenOrderInput);
    };

}
