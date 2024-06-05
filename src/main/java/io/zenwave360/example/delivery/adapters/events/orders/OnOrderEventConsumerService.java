package io.zenwave360.example.delivery.adapters.events.orders;

import io.zenwave360.example.orders.core.domain.events.OrderEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("delivery_OnOrderEventConsumerService")
public class OnOrderEventConsumerService extends AbstractBaseConsumer {

    @Async
    @TransactionalEventListener
    public void onOrderEvent(OrderEvent payload) {
        log.debug("Received command request for onOrderEvent: {}", payload);
        var deliveryInput = mapper.asDeliveryInput(payload);
        deliveryService.createDelivery(deliveryInput);
    };

}
