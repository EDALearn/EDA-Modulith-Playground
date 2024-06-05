package io.zenwave360.example.orders.adapters.events.delivery;

import io.zenwave360.example.delivery.core.domain.events.DeliveryStatusUpdated;
import io.zenwave360.example.orders.core.inbound.OrdersService;
import io.zenwave360.example.orders.core.inbound.dtos.DeliveryStatus;
import io.zenwave360.example.orders.core.inbound.dtos.DeliveryStatusInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class OnDeliveryStatusUpdatedConsumerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OrdersService ordersService;

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /** */
    @Async
    @TransactionalEventListener
    public void onDeliveryStatusUpdated(DeliveryStatusUpdated payload) {
        log.debug("Received command request for onDeliveryStatusUpdated: {}", payload);
        DeliveryStatusInput input = new DeliveryStatusInput() //
            .setDeliveryOrderId(payload.getDeliveryId())
            .setDeliveryStatus(map(payload.getStatus()));
        ordersService.updateDeliveryStatus(payload.getCustomerOrderId(), input);
    };

    private DeliveryStatus map(io.zenwave360.example.delivery.core.domain.DeliveryOrderStatus status) {
        return DeliveryStatus.valueOf(status.name());
    }

}
